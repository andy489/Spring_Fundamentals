package com.coffeeshop.web;

import com.coffeeshop.model.dto.OrderAddDto;
import com.coffeeshop.service.OrderService;
import com.coffeeshop.session.CurrentUser;
import jakarta.validation.Valid;
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
@RequestMapping("/orders")
public class OrderController extends BaseController {

    private final OrderService orderService;
    private final CurrentUser currentUser;

    public OrderController(OrderService orderService, CurrentUser currentUser) {
        this.orderService = orderService;
        this.currentUser = currentUser;
    }

    @ModelAttribute(name = "orderAddModel")
    public OrderAddDto initOrderAddModel() {
        return new OrderAddDto();
    }

    @GetMapping("/add")
    public ModelAndView getAddTask() {
        if (!currentUser.isLoggedIn()) {
            return super.redirect("/users/login");
        }

        return super.view("order-add");
    }

    @PostMapping("/add")
    public ModelAndView postAddTask(
            @Valid @ModelAttribute(name = "orderAddModel") OrderAddDto orderAddDto,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        if (!currentUser.isLoggedIn()) {
            return super.redirect("/users/login");
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("orderAddModel", orderAddDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.orderAddModel", bindingResult);

            return super.redirect("add");
        }

        this.orderService.placeOrder(orderAddDto);

        return super.redirect("/home");
    }

    @GetMapping("/ready")
    public ModelAndView orderReady(@RequestParam(name = "id") Long orderId) {
        if (!currentUser.isLoggedIn()) {
            return super.redirect("/users/login");
        }

        this.orderService.readyOrder(orderId);

        return super.redirect("/home");
    }
}
