package com.example.bonapetit.controller;

import com.example.bonapetit.model.dto.RecipeAddDto;
import com.example.bonapetit.service.RecipeService;
import com.example.bonapetit.session.CurrentUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/recipe")
public class RecipeController extends GenericController{

    private final RecipeService recipeService;

    private final CurrentUser currentUser;

    @Autowired
    public RecipeController(RecipeService recipeService, CurrentUser currentUser) {
        this.recipeService = recipeService;
        this.currentUser = currentUser;
    }

    @ModelAttribute(name = "recipeAddModel")
    public RecipeAddDto initRecipeAddModel() {
        return new RecipeAddDto();
    }

    @GetMapping("/add")
    public ModelAndView getAddRecipe() {

        if (!currentUser.isLoggedIn()) {
            return super.redirect("/users/login");
        }

        return super.view("recipe-add");
    }

    @PostMapping("/add")
    public ModelAndView postAddRecipe(
            @Valid @ModelAttribute(name = "recipeAddModel") RecipeAddDto recipeAddDto,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (!currentUser.isLoggedIn()) {
            return super.redirect("/users/login");
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("recipeAddModel", recipeAddDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.recipeAddModel", bindingResult);

            return super.redirect("add");
        }

        this.recipeService.addRecipe(recipeAddDto);

        return super.redirect("/home");
    }

}
