package com.spotifly.model.entity;


import com.spotifly.model.enumerated.StyleEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity(name = "styles")
@Getter
@Setter
@Accessors(chain = true)
public class StyleEntity extends BaseEntity {

    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private StyleEnum name;

    @Column(nullable = false)
    private String description;

}
