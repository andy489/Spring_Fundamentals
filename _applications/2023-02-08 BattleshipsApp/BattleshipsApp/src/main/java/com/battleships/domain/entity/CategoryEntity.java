package com.battleships.domain.entity;

import com.battleships.domain.enumerated.ShipType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Lob;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Entity(name = "categories")
@Getter
@Setter
@Accessors(chain = true)
@ToString
public class CategoryEntity extends BaseEntity {

    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.ORDINAL)
    private ShipType name;

    @Lob
    private String description;

}
