package com.coffeeshop.web;

import com.coffeeshop.service.OrderService;
import com.coffeeshop.session.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController extends BaseController{

    private final CurrentUser currentUser;
    private final OrderService orderService;

    @Autowired
    public HomeController(
            CurrentUser currentUser,
            OrderService orderService
    ) {
        this.currentUser = currentUser;
        this.orderService = orderService;
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

        mav.addObject("ordersModel", orderService.getOrdersModel());

        mav.setViewName("home");
        return mav;
    }

}
