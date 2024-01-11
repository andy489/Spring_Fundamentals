package com.example.bonapetit.model.view;

import com.example.bonapetit.model.enumerated.CategoryEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

@Getter
@Setter
@Accessors(chain = true)
@ToString
public class RecipeView {

    private Long id;

    private String name;

    private String ingredients;

    private CategoryEnum category;

}
