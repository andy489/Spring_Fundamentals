package com.reseller.model.validation.register;

import com.reseller.service.AuthService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private final AuthService authService;

    public UniqueEmailValidator(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return authService.getByEmail(value).isEmpty();
    }

}
