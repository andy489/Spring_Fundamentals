package com.battleships.domain.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Setter
@Getter
@Accessors(chain = true)
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@ToString
public class BattleDto {

    @NotNull
    @Positive
    private Long loggedUserShipId;

    @NotNull
    @Positive
    private Long notLoggedUserShipId;

}