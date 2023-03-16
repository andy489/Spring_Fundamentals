package com.battleships.domain.dto;

import com.battleships.domain.entity.enums.ShipType;
import com.battleships.domain.validation.ship.UniqueShipName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@ToString
public class ShipAddDto {

    @NotBlank
    @Size(min = 2, max = 10, message = "must be between 2 and 10 characters")
    @UniqueShipName(message = "name should be unique")
    private String name;

    @NotNull
    @Positive(message = "health must be positive")
    private Long health;

    @NotNull
    @Positive(message = "power must be positive")
    private Long power;

    @NotNull
    @PastOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date created;

    @NotNull
    private ShipType shipType;

}
