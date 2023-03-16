package com.battleships.web;

import com.battleships.domain.dto.ShipAddDto;
import com.battleships.service.ShipService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/ships")
public class ShipController {

    private final ShipService shipService;

    @Autowired
    public ShipController(ShipService shipService) {
        this.shipService = shipService;
    }

    @ModelAttribute(name = "shipAddModel")
    public ShipAddDto initShipAddModel() {
        return new ShipAddDto();
    }

    @GetMapping("/add")
    public String getAddShip() {
        return "ship-add";
    }

    @PostMapping("/add")
    public String postAddShip(
            @Valid @ModelAttribute(name = "shipAddModel") ShipAddDto shipAddDto,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("shipAddModel", shipAddDto)
                    .addFlashAttribute("org.springframework.validation.BindingResult.shipAddModel", bindingResult);

            return "redirect:add";
        }

        if (shipService.addShip(shipAddDto)) {
            return "redirect:/home";
        }

        return "redirect:add";
    }

}
