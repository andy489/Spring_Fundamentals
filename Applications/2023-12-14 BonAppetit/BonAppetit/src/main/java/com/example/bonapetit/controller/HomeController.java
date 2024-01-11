package com.example.bonapetit.controller;

import com.example.bonapetit.service.RecipeService;
import com.example.bonapetit.session.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController extends GenericController {

    private final CurrentUser currentUser;

    private final RecipeService recipeService;

    public HomeController(CurrentUser currentUser, RecipeService recipeService) {
        this.currentUser = currentUser;
        this.recipeService = recipeService;
    }

    @GetMapping(path = {"/", "/index"})
    public ModelAndView getIndex() {

        return super.view("index");
    }

    @GetMapping(path = "/home")
    public ModelAndView getHome(ModelAndView mav) {

        if (!currentUser.isLoggedIn()) {
            return super.redirect("/users/login");
        }

        mav.addObject("recipesHomeModel", recipeService.getRecipesHomeModel());

        return super.view("home", mav);
    }

    @PostMapping("/recipe/like/{id}")
    public ModelAndView like(@PathVariable("id") Long recipeId, ModelAndView mav) {

        if (!currentUser.isLoggedIn()) {
            return super.redirect("/users/login");
        }

        recipeService.like(recipeId);

        mav.addObject("recipesHomeModel", recipeService.getRecipesHomeModel());

        return super.view("home", mav);
    }

    @PostMapping("/recipe/unlike/{id}")
    public ModelAndView unlike(@PathVariable("id") Long recipeId, ModelAndView mav) {

        if (!currentUser.isLoggedIn()) {
            return super.redirect("/users/login");
        }

        recipeService.unlike(recipeId);

        mav.addObject("recipesHomeModel", recipeService.getRecipesHomeModel());

        return super.view("home", mav);
    }

}
