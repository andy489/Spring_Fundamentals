package com.spring.fundamentals.bootdemo.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GreetingController {

    private final String greetingMsg;

    public GreetingController(@Value("${demo.greeting.message}") String greetingMsg) {

        this.greetingMsg = greetingMsg;
    }

    @GetMapping("/demo")
    public ModelAndView test(ModelAndView modelAndView) {

        modelAndView.addObject("greeting", greetingMsg);
        modelAndView.setViewName("demo");

        return modelAndView;
    }

}
