package com.example.bonapetit.model.view;

import com.example.bonapetit.model.enumerated.CategoryEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Accessors(chain = true)
public class RecipeHomeModel {

    private Map<CategoryEnum, List<RecipeView>> recipes;

    private List<RecipeView> emptyList = new ArrayList<>();

    private List<RecipeView> favourites;
}
