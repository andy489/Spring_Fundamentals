package com.shoppinglist.web;

import com.shoppinglist.service.ProductService;
import com.shoppinglist.session.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController extends BaseController {

    private final CurrentUser currentUser;
    private final ProductService productService;

    @Autowired
    public HomeController(
            CurrentUser currentUser,
            ProductService productService
    ) {
        this.currentUser = currentUser;
        this.productService = productService;
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

        mav.addObject("productsByCategory", productService.getProductsByCategory());

        mav.setViewName("home");
        return mav;
    }

}
