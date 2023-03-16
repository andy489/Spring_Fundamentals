package com.spotifly.web;

import com.spotifly.model.dto.UserLoginDto;
import com.spotifly.model.dto.UserRegisterDto;
import com.spotifly.service.UserService;
import com.spotifly.session.CurrentUser;
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
@RequestMapping("/users")
public class UserController extends BaseController {

    private final UserService userService;
    private final CurrentUser currentUser;

    @Autowired
    public UserController(UserService userService, CurrentUser currentUser) {
        this.userService = userService;
        this.currentUser = currentUser;
    }

    @ModelAttribute(name = "userRegisterModel")
    public UserRegisterDto initUserRegisterDto() {
        return new UserRegisterDto();
    }

    @ModelAttribute(name = "userLoginModel")
    public UserLoginDto initUserLoginDto() {
        return new UserLoginDto();
    }

    @GetMapping("/register")
    public ModelAndView getRegister() {

        return super.view("users/register");
    }

    @GetMapping("/login")
    public ModelAndView getLogin() {

        return super.view("users/login");
    }

    @PostMapping("/register")
    public ModelAndView postRegister(
            @Valid @ModelAttribute(name = "userRegisterModel") UserRegisterDto userRegisterDto,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegisterModel", userRegisterDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterModel", bindingResult);

            return super.redirect("register");
        }

        this.userService.registerAndLogin(userRegisterDto);

        return super.redirect("/home");
    }

    @PostMapping("/login")
    public ModelAndView postLogin(
            @Valid @ModelAttribute(name = "userLoginModel") UserLoginDto userLoginDto,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("userLoginModel", userLoginDto)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userLoginModel", bindingResult);

            return super.redirect("login");
        }

        userService.login(userLoginDto);

        return super.redirect("/home");
    }

    @GetMapping("/logout")
    public ModelAndView getLogout() {
        userService.logout();

        return super.redirect("/index");
    }

    @GetMapping("/remove-all-songs-from-playlist")
    public ModelAndView deleteAllFromPlaylist() {
        if (!currentUser.isLoggedIn()) {
            return super.redirect("/home");
        }

        userService.deleteAllSongs();

        return super.redirect("/home");
    }

}
