package com.spotify.web;

import com.spotify.service.SongService;
import com.spotify.session.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController extends BaseController {
    private final CurrentUser currentUser;
    private final SongService songService;

    @Autowired
    public HomeController(
            CurrentUser currentUser,
            SongService songService
    ) {
        this.currentUser = currentUser;
        this.songService = songService;
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

        mav
                .addObject("songsByGenre", songService.getSongsByGenre())
                .addObject("myPlaylist", songService.getMyPlaylist(currentUser.getId()))
                .setViewName("home");

        return mav;
    }

}
