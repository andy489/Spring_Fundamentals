package com.shoppinglist.model.dto;

import com.shoppinglist.model.enumerated.CategoryEnum;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Accessors(chain = true)
public class ProductAddDto {

    @NotBlank
    @Size(min = 3, max = 20, message = "length must be between 3 and 20 characters")
    private String name;

    @NotBlank
    @Size(min = 5, message = "length must be more than 5 characters")
    private String description;

    @NotNull
    @FutureOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime neededBefore;

    @NotNull
    @Positive
    private BigDecimal price;

    @NotNull
    private CategoryEnum category;
}
