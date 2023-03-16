package com.gira.model.validation.task;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = UniqueTaskNameValidator.class)
public @interface UniqueTaskName {

    String message() default "task name duplication";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
