package com.likebook.web;

import com.likebook.model.dto.PostAddDto;
import com.likebook.service.PostService;
import com.likebook.session.CurrentUser;
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
@RequestMapping("/posts")
public class PostController extends BaseController {

    private final PostService postService;
    private final CurrentUser currentUser;

    @Autowired
    public PostController(PostService postService, CurrentUser currentUser) {
        this.postService = postService;
        this.currentUser = currentUser;
    }

    @ModelAttribute(name = "postAddModel")
    public PostAddDto initPostAddModel() {
        return new PostAddDto();
    }

    @GetMapping("/add")
    public ModelAndView getAddTask() {
        if (!currentUser.isLoggedIn()) {
            return super.redirect("/users/login");
        }

        return super.view("post-add");
    }

    @PostMapping("/add")
    public ModelAndView postAddTask(
            @Valid @ModelAttribute(name = "postAddModel") PostAddDto postAddDto,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        if (!currentUser.isLoggedIn()) {
            return super.redirect("/users/login");
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("postAddModel", postAddDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.postAddModel", bindingResult);

            return super.redirect("add");
        }

        this.postService.addPost(postAddDto);

        return super.redirect("/home");
    }

    @GetMapping("/remove")
    public ModelAndView remove(@RequestParam("id") Long postId) {
        postService.likePostWithId(postId);
        postService.removePostById(postId);
        return super.redirect("/home");
    }

    @GetMapping("/like")
    public ModelAndView like(@RequestParam("id") Long postId) {
        postService.likePostWithId(postId);

        return super.redirect("/home");
    }
}
