package com.coffeeshop.model.enumerated;

import lombok.Getter;

@Getter
public enum CategoryEnum {
    COFFEE(2, "Coffee", "coffee"),
    DRINK(1, "Drink", "drink"),
    CAKE(10, "Cake", "cake"),
    OTHER(5, "Other", "other");

    private final Integer neededTime;
    private final String displayName;
    private final String imgName;

    CategoryEnum(Integer neededTime, String displayName, String imgName) {
        this.neededTime = neededTime;
        this.displayName = displayName;
        this.imgName = imgName;
    }
}
