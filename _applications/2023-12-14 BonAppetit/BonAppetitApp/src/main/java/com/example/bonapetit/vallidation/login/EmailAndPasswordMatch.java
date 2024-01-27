package com.example.bonapetit.vallidation.login;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = EmailAndPasswordMatchValidator.class)
public @interface EmailAndPasswordMatch {

    String uniqueField();

    String passwordField();

    String message() default "{failed.login}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
