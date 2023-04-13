package com.planner.model.enumerated;

import lombok.Getter;

@Getter
public enum PriorityEnum {
    URGENT("Urgent", "An urgent problem that blocks the system use until the issue is resolved."),
    IMPORTANT("Important", "A core functionality that your product is explicitly supposed to perform is compromised."),
    LOW("Low", "Should be fixed if time permits but can be postponed.");

    private final String description;
    private final String displayName;

    PriorityEnum(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }
}
