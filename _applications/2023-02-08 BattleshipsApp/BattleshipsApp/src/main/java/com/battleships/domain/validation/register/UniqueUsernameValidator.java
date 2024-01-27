package com.battleships.domain.validation.register;

import com.battleships.service.AuthService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    private final AuthService authService;

    @Autowired
    public UniqueUsernameValidator(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return authService.getByUsername(value).isEmpty();
    }

}
