package com.battleships.domain.entity.enums;

import lombok.Getter;

@Getter
public enum ShipType {
    BATTLE("Battle"),
    CARGO("Cargo"),
    PATROL("Patrol");

    ShipType(String displayName) {
        this.displayName = displayName;
    }

    private final String displayName;
}
