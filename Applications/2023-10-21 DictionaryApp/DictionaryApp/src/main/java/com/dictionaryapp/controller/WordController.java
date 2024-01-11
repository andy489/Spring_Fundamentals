package com.dictionaryapp.controller;

import com.dictionaryapp.model.dto.WordAddDto;
import com.dictionaryapp.service.WordService;
import com.dictionaryapp.session.CurrentUser;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

@Controller
@RequestMapping("/words")
public class WordController extends GenericController {

    private final WordService wordService;

    private final CurrentUser currentUser;

    @Autowired
    public WordController(WordService wordService, CurrentUser currentUser) {
        this.wordService = wordService;
        this.currentUser = currentUser;
    }

    @ModelAttribute(name = "wordAddModel")
    public WordAddDto initWordAddModel() {
        return new WordAddDto();
    }

    @GetMapping("/add")
    public ModelAndView getAddWord() {

        if (!currentUser.isLoggedIn()) {
            return super.redirect("/users/login");
        }

        return super.view("word-add");
    }

    @PostMapping("/add")
    public ModelAndView postAddWord(
            @Valid @ModelAttribute(name = "wordAddModel") WordAddDto wordAddDto,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (!currentUser.isLoggedIn()) {
            return super.redirect("/users/login");
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("wordAddModel", wordAddDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.wordAddModel", bindingResult);

            return super.redirect("add");
        }

        this.wordService.addWord(wordAddDto);

        return super.redirect("/home");
    }

    @Transactional
    @PostMapping("/remove/{id}")
    public String removeWord(@PathVariable(name = "id") UUID wordId) {
        wordService.removeWord(wordId);

        return "redirect:/home";
    }

    @PostMapping("/remove-all")
    public String removeAllWords() {
        wordService.removeAllWords();

        return "redirect:/home";
    }
}
