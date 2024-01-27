package com.dictionaryapp.controller;

import com.dictionaryapp.service.WordService;
import com.dictionaryapp.session.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController extends GenericController {

    private final CurrentUser currentUser;

    private final WordService wordService;

    public HomeController(CurrentUser currentUser, WordService wordService) {
        this.currentUser = currentUser;
        this.wordService = wordService;
    }

    @GetMapping(path = "/home")
    public ModelAndView getHome(ModelAndView mav) {

        if (!currentUser.isLoggedIn()) {
            return super.redirect("/users/login");
        }

        mav.addObject("wordsHomeModel", wordService.getWordsHomeModel());

        return super.view("home", mav);
    }

    @GetMapping(path = {"/", "/index"})
    public ModelAndView getIndex() {

        return super.view("index");
    }
}
