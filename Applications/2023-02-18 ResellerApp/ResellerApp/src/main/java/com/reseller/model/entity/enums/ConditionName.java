package com.reseller.model.entity.enums;

import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(chain = true)
public enum ConditionName {
    EXCELLENT("Excellent", "In perfect condition"),
    GOOD("Good", "Some signs of wear and tear or minor defects"),
    ACCEPTABLE("Acceptable", "The item is fairly worn but continues to function properly");

    private final String displayName;
    private final String description;

    ConditionName(
            String displayName,
            String description
    ) {
        this.displayName = displayName;
        this.description = description;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getDescription() {
        return description;
    }
}
