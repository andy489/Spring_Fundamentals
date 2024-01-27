package com.gira.model.dto;

import com.gira.model.enumerated.ClassificationEnum;
import com.gira.model.validation.task.UniqueTaskName;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@Accessors(chain = true)
public class TaskAddDto {

    @NotBlank
    @Size(min = 3, max = 20, message = "length must be between 3 and 20 characters")
    @UniqueTaskName
    private String name;

    @NotBlank
    @Size(min = 5, message = "length must be more than 5 characters")
    private String description;

    @NotNull
    @FutureOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dueDate;

    @NotNull
    private ClassificationEnum classification;

}
