package com.spotify.model.enumerated;

public enum StyleEnum {
    POP("Pop", "pop"),
    ROCK("Rock", "rock"),
    JAZZ("Jazz", "jazz"),
    HOUSE("House", "house"),
    TRANCE("Trance", "trance"),
    CHILL_OUT("Chill Out & Relax", "relax");

    private final String displayName;
    private final String imgName;

    StyleEnum(
            String displayName,
            String imgName
    ) {

        this.displayName = displayName;
        this.imgName = imgName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getImgName() {
        return imgName;
    }

}
