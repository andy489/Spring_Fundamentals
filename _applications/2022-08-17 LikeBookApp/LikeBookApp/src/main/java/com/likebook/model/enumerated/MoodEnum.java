package com.likebook.model.enumerated;

import lombok.Getter;

@Getter
public enum MoodEnum {
    HAPPY("Happy", "Clap along if you feel like happiness is the truth!"),
    SAD("Sad", "Some days are just bad days, that's all."),
    INSPIRED("Inspired", "Nothing is impossible!");

    private final String displayName;
    private final String description;

    MoodEnum(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }
}
