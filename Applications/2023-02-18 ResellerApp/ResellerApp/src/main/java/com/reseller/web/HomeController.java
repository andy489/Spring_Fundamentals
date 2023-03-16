package com.reseller.web;

import com.reseller.service.OfferService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    private final OfferService offerService;

    public HomeController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping
    public String getIndex() {

        return "index";
    }

    @GetMapping("/home")
    public ModelAndView getHome(ModelAndView mav) {

        mav.addObject("offerModel", offerService.getOffers());

        mav.setViewName("home");

        return mav;
    }


    @Transactional
    @GetMapping("/remove/{id}")
    public String deleteOffer(@PathVariable(name = "id") Long offerId) {
        Boolean removed = offerService.removeOffer(offerId);

        return "redirect:/home";
    }


    @GetMapping("/buy")
    public String buyOffer(@RequestParam(name = "id") Long offerId) {
        Boolean removed = offerService.buyOffer(offerId);

        return "redirect:/home";
    }
}
