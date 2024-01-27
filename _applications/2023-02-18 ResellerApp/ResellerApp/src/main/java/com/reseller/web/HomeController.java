package com.reseller.web;

import com.reseller.service.OfferService;
import com.reseller.session.CurrentUser;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    private final OfferService offerService;
    private final CurrentUser currentUser;

    public HomeController(OfferService offerService, CurrentUser currentUser) {

        this.offerService = offerService;
        this.currentUser = currentUser;
    }

    @GetMapping({"/", "/index"})
    public String getIndex() {

        return "index";
    }

    @GetMapping("/home")
    public ModelAndView getHome(ModelAndView mav) {
        if (!currentUser.getLoggedIn()) {
            mav.setViewName("redirect:/auth/login");
            return mav;
        }

        mav.addObject("offerModel", offerService.getOffers());

        mav.setViewName("home");

        return mav;
    }

    @Transactional
    @GetMapping("/remove/{id}")
    public String deleteOffer(@PathVariable(name = "id") Long offerId) {
        if (!currentUser.getLoggedIn()) {
            return "redirect:/auth/login";
        }

        offerService.removeOffer(offerId);

        return "redirect:/home";
    }


    @GetMapping("/buy")
    public String buyOffer(@RequestParam(name = "id") Long offerId) {
        if (!currentUser.getLoggedIn()) {
            return "redirect:/auth/login";
        }

        offerService.buyOffer(offerId);

        return "redirect:/home";
    }
}
