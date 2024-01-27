package com.mymusicdb.web;

import com.mymusicdb.model.dto.AlbumAddDto;
import com.mymusicdb.service.AlbumService;
import com.mymusicdb.session.CurrentUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/albums")
public class AlbumController extends BaseController {

    private final AlbumService albumService;
    private final CurrentUser currentUser;

    @Autowired
    public AlbumController(AlbumService albumService, CurrentUser currentUser) {
        this.albumService = albumService;
        this.currentUser = currentUser;
    }

    @ModelAttribute(name = "albumAddModel")
    public AlbumAddDto initAlbumAddModel() {
        return new AlbumAddDto();
    }

    @GetMapping("/add")
    public ModelAndView getAddAlbum() {
        if (!currentUser.isLoggedIn()) {
            return super.redirect("/users/login");
        }

        return super.view("add-album");
    }

    @PostMapping("/add")
    public ModelAndView postAddAlbum(
            @Valid @ModelAttribute(name = "albumAddModel") AlbumAddDto albumAddDto,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        if (!currentUser.isLoggedIn()) {
            return super.redirect("/users/login");
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("albumAddModel", albumAddDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.albumAddModel", bindingResult);

            return super.redirect("add");
        }

        this.albumService.addAlbum(albumAddDto);

        return super.redirect("/home");
    }

    @GetMapping("remove")
    public ModelAndView removeAlbum(@RequestParam("id") Long albumId) {
        albumService.removeAlbum(albumId);

        return super.redirect("/home");
    }

}