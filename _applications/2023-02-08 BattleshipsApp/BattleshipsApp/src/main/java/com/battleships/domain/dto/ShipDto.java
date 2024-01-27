package com.battleships.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@ToString
public class ShipDto {

    private Long id;

    private String name;

    private Integer health;

    private Integer power;

}
