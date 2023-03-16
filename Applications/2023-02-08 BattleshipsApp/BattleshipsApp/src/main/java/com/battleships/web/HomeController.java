package com.battleships.web;

import com.battleships.domain.dto.BattleDto;
import com.battleships.domain.dto.UserWithShipsDto;
import com.battleships.domain.entity.ShipEntity;
import com.battleships.service.ShipService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class HomeController {

    private final ShipService shipService;

    @Autowired
    public HomeController(ShipService shipService) {
        this.shipService = shipService;
    }

    @ModelAttribute(name = "battleModel")
    public BattleDto initBattleDto() {

        return new BattleDto();
    }

    @ModelAttribute(name = "allShips")
    public List<ShipEntity> initAllShips() {

        return shipService.getAllSorted();
    }

    @GetMapping
    public String getIndex() {

        return "index";
    }

    @GetMapping("/home")
    public ModelAndView getHome(ModelAndView mav) {
        UserWithShipsDto leftUserWithShips = shipService.getUserWithShips(true);
        UserWithShipsDto rightUserWithShips = shipService.getUserWithShips(false);

        mav.addObject("left", leftUserWithShips)
                .addObject("right", rightUserWithShips)
                .setViewName("home");

        return mav;
    }

    @PostMapping("/battle")
    public String postBattle(
            @Valid @ModelAttribute(name = "battleModel") BattleDto battleDto,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("battleModel", battleDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.battleModel", bindingResult);

            return "redirect:home";
        }

        redirectAttributes.addFlashAttribute("battleModel", battleDto);

        shipService.fight(battleDto);

        return "redirect:home";
    }

}
