package com.example.bonapetit.model.dto;

import com.example.bonapetit.model.enumerated.CategoryEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class RecipeAddDto {

    @NotBlank
    @Size(min = 2, max = 40, message = "{recipe.name.size}")
    private String name;

    @NotBlank
    @Size(min = 2, max = 80, message = "{recipe.ingredients.size}")
    private String ingredients;

    @NotNull
    private CategoryEnum category;
}
