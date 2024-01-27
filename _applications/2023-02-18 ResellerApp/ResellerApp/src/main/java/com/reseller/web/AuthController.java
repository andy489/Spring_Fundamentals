package com.reseller.web;

import com.reseller.model.dto.UserLoginDto;
import com.reseller.model.dto.UserRegisterDto;
import com.reseller.service.AuthService;
import com.reseller.session.CurrentUser;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(path = "/auth")
public class AuthController {
    private final AuthService authService;

    private final CurrentUser currentUser;

    public AuthController(AuthService authService, CurrentUser currentUser) {
        this.authService = authService;
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
    public String getRegister() {
        return "register";
    }

    @PostMapping("/register")
    public String postRegister(@Valid @ModelAttribute(name = "userRegisterModel") UserRegisterDto userRegisterDto,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (currentUser.getLoggedIn()) {
            return "redirect:/home";
        }

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("userRegisterModel", userRegisterDto)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userRegisterModel", bindingResult);

            return "redirect:register";
        }

        authService.registerAndLogin(userRegisterDto);

        return "redirect:/home";
    }

    @GetMapping("/login")
    public String getLogin() {

        return "login";
    }

    @PostMapping("/login")
    public String postLogin(@Valid @ModelAttribute(name = "userLoginDto") UserLoginDto userLoginDto,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (currentUser.getLoggedIn()) {
            return "redirect:/home";
        }

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("userLoginModel", userLoginDto)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userLoginModel", bindingResult);

            return "redirect:login";
        }

        authService.login(userLoginDto);

        return "redirect:/home";
    }

    @PostMapping("/logout")
    public String getLogout() {
        if (!currentUser.getLoggedIn()) {
            return "redirect:/home";
        }

        authService.logout();

        return "redirect:/";
    }
}
