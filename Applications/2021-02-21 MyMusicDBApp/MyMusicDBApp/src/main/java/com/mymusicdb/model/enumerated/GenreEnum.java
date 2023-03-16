package com.mymusicdb.model.enumerated;

import lombok.Getter;

@Getter
public enum GenreEnum {
    ROCK("Rock", "rock"),
    POP("Pop", "pop"),
    METAL("Metal", "metal"),
    OTHER("Other", "other");

    private final String displayName;
    private final String imgName;

    GenreEnum(String displayName, String imgName) {
        this.displayName = displayName;
        this.imgName = imgName;
    }
}
