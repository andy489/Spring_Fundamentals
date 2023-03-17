package com.state.management.demo.web;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CookieController {

    private static final String LANG_COOKIE_NAME = "lang";

    @GetMapping("/cookies")
    public String cookies(Model model,
                          @CookieValue(
                                  name = LANG_COOKIE_NAME,
                                  defaultValue = "en"
                          ) String lang) {

        model.addAttribute(LANG_COOKIE_NAME, lang);

        return "cookies";
    }


    @PostMapping("/cookies")
    public String cookies(
            HttpServletResponse httpServletResponse,
            @RequestParam(name = "languageAbb") String langAbb
    ){

        Cookie cookie = new Cookie(LANG_COOKIE_NAME, langAbb);

        httpServletResponse.addCookie(cookie);

        return "redirect:/cookies";
    }

}
