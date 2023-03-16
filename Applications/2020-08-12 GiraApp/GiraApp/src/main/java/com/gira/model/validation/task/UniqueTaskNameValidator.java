package com.gira.model.validation.task;

import com.gira.service.TaskService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueTaskNameValidator implements ConstraintValidator<UniqueTaskName, String> {

    private final TaskService taskService;

    public UniqueTaskNameValidator(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        return taskService.getByName(name).isEmpty();
    }

}
