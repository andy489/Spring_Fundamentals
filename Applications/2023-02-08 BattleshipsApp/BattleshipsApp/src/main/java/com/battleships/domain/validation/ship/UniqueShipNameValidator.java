package com.battleships.domain.validation.ship;

import com.battleships.service.ShipService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueShipNameValidator implements ConstraintValidator<UniqueShipName, String> {

    private final ShipService shipService;

    @Autowired
    public UniqueShipNameValidator(ShipService shipService) {
        this.shipService = shipService;
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        return shipService.getByName(name).isEmpty();
    }

}