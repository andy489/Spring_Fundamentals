package com.reseller.web;

import com.reseller.model.dto.OfferAddDto;
import com.reseller.service.OfferService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/offers")
public class OfferController {

    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @ModelAttribute(name = "offerAddModel")
    public OfferAddDto initOfferAddDto() {
        return new OfferAddDto();
    }

    @GetMapping("/add")
    public String getAddOffer() {
        return "offer-add";
    }

    @PostMapping("/add")
    public String postAddOffer(
            @Valid @ModelAttribute(name = "offerAddModel") OfferAddDto offerAddDto,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("offerAddModel", offerAddDto)
                    .addFlashAttribute("org.springframework.validation.BindingResult.offerAddModel", bindingResult);

            return "redirect:add";
        }

        if (offerService.addOffer(offerAddDto)) {
            return "redirect:/home";
        }

        return "redirect:add";
    }
}
