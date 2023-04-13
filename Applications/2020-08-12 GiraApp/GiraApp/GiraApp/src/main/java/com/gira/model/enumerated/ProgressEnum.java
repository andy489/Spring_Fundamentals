package com.gira.model.enumerated;

import lombok.Getter;

@Getter
public enum ProgressEnum {

    OPEN("Open"),
    IN_PROGRESS("In Progress"),
    COMPLETED("Completed");

    private final String displayName;

    ProgressEnum(String displayName) {
        this.displayName = displayName;
    }

}
