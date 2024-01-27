package com.spotifly.web;

import com.spotifly.model.dto.SongAddDto;
import com.spotifly.service.SongService;
import com.spotifly.session.CurrentUser;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/songs")
public class SongController extends BaseController {

    private final SongService songService;
    private final CurrentUser currentUser;

    public SongController(
            SongService songService,
            CurrentUser currentUser
    ) {
        this.songService = songService;
        this.currentUser = currentUser;
    }

    @ModelAttribute(name = "songAddModel")
    public SongAddDto initSongAddModel() {
        return new SongAddDto();
    }

    @GetMapping("/add")
    public ModelAndView getAddTask() {
        if (!currentUser.isLoggedIn()) {
            return super.redirect("/users/login");
        }

        return super.view("song-add");
    }

    @PostMapping("/add")
    public ModelAndView postAddTask(
            @Valid @ModelAttribute(name = "songAddModel") SongAddDto songAddDto,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        if (!currentUser.isLoggedIn()) {
            return super.redirect("/users/login");
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("songAddModel", songAddDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.songAddModel", bindingResult);

            return super.redirect("add");
        }

        this.songService.addSong(songAddDto);

        return super.redirect("/home");
    }

    @GetMapping("/add-song-to-playlist/{id}")
    public ModelAndView addSongToPlaylist(
            @PathVariable("id") Long songId
    ) {
        if (!currentUser.isLoggedIn()) {
            return super.redirect("/users/login");
        }

        songService.addSongToPlaylist(songId);

        return super.redirect("/home");
    }

    @GetMapping("/remove-song-from-playlist/{id}")
    public ModelAndView removeSongToPlaylist(
            @PathVariable("id") Long songId
    ) {
        if (!currentUser.isLoggedIn()) {
            return super.redirect("/users/login");
        }

        songService.removeSongToPlaylist(songId);

        return super.redirect("/home");
    }

}
