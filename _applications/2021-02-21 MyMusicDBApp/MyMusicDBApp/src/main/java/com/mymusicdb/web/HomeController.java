package com.mymusicdb.web;

import com.mymusicdb.service.AlbumService;
import com.mymusicdb.session.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HomeController extends BaseController {

    private final CurrentUser currentUser;
    private final AlbumService albumService;

    public HomeController(CurrentUser currentUser, AlbumService albumService) {
        this.currentUser = currentUser;
        this.albumService = albumService;
    }

    @GetMapping({"/", "/index"})
    public ModelAndView getIndex() {
        if (currentUser.isLoggedIn()) {
            return super.redirect("/home");
        }

        return super.view("index");
    }

    @GetMapping("/home")
    public ModelAndView getHome(ModelAndView mav) {
        if (!currentUser.isLoggedIn()) {
            return super.redirect("/index");
        }

        mav.addObject("homeDataModel", albumService.getData());

        mav.setViewName("home");

        return mav;
    }
}
