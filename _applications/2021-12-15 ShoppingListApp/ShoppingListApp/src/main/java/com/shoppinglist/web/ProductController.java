package com.shoppinglist.web;

import com.shoppinglist.model.dto.ProductAddDto;
import com.shoppinglist.service.ProductService;
import com.shoppinglist.session.CurrentUser;
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
@RequestMapping("products")
public class ProductController extends BaseController {

    private final ProductService productService;
    private final CurrentUser currentUser;

    @Autowired
    public ProductController(ProductService productService, CurrentUser currentUser) {
        this.productService = productService;
        this.currentUser = currentUser;
    }

    @ModelAttribute(name = "productAddModel")
    public ProductAddDto initSongAddModel() {
        return new ProductAddDto();
    }

    @GetMapping("/add")
    public ModelAndView getAddTask() {
        if (!currentUser.isLoggedIn()) {
            return super.redirect("/users/login");
        }

        return super.view("product-add");
    }

    @PostMapping("/add")
    public ModelAndView postAddProduct(
            @Valid @ModelAttribute(name = "productAddModel") ProductAddDto productAddDto,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        if (!currentUser.isLoggedIn()) {
            return super.redirect("/users/login");
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("productAddModel", productAddDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.productAddModel", bindingResult);

            return super.redirect("add");
        }

        this.productService.addProduct(productAddDto);

        return super.redirect("/home");
    }

    @GetMapping("/buy")
    public ModelAndView buyProduct(
            @RequestParam("id") Long productId
    ) {
        if (!currentUser.isLoggedIn()) {
            return super.redirect("/users/login");
        }

        productService.buyProduct(productId);

        return super.redirect("/home");
    }

    @GetMapping("/buy-all")
    public ModelAndView buyAllProduct() {
        if (!currentUser.isLoggedIn()) {
            return super.redirect("/users/login");
        }

        productService.buyAllProducts();

        return super.redirect("/home");
    }

}
