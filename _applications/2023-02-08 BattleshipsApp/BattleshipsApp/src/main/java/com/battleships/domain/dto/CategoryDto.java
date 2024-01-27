package com.battleships.domain.dto;

import com.battleships.domain.enumerated.ShipType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class CategoryDto {

    private Long id;
    private ShipType categoryType;
    private String description;

}
