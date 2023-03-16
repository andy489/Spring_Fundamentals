package com.shoppinglist.model.enumerated;

import lombok.Getter;

@Getter
public enum CategoryEnum {
    FOOD("Food", "Food category"),
    DRINK("Drink", "Drink category"),
    HOUSEHOLD("Household", "Household category"),
    OTHER("Other", "Other category");

    private final String displayName;
    private final String description;

    CategoryEnum(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }
}
