package com.example.bonapetit.model.enumerated;

import lombok.Getter;

@Getter
public enum CategoryEnum {
    MAIN_DISH("Main dish", "Heart of the meal, substantial and satisfying; main dish delights taste buds."),
    DESSERT("Dessert", "Sweet finale, indulgent and delightful; dessert crowns the dining experience with joy."),
    COCKTAIL("Cocktail", "Sip of sophistication, cocktails blend flavors, creating a spirited symphony in every glass.");

    private final String displayName;
    private final String description;

    CategoryEnum(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }
}
