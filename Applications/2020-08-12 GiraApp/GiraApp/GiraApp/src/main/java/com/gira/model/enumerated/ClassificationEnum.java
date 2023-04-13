package com.gira.model.enumerated;

import lombok.Getter;

@Getter
public enum ClassificationEnum {

    BUG("Bug", "High priority - fix as soon as possible"),
    FEATURE("Feature", "Normal priority"),
    SUPPORT("Support", "Low priority - fix when you have time"),
    OTHER("Other", "Priority to be specified");

    private final String displayName;
    private final String description;

    ClassificationEnum(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }
}
