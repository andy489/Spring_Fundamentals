package com.likebook.web;

import com.likebook.service.PostService;
import com.likebook.session.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HomeController extends BaseController{

    private final CurrentUser currentUser;
    private final PostService postService;

    public HomeController(CurrentUser currentUser, PostService postService) {
        this.currentUser = currentUser;
        this.postService = postService;
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

        mav.addObject("postModel", postService.getData());

        mav.setViewName("home");

        return mav;
    }
}
