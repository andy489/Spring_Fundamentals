package com.likebook.model.entity;

import com.likebook.model.enumerated.MoodEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity(name = "moods")
@Getter
@Setter
@Accessors(chain = true)
public class MoodEntity extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private MoodEnum name;

    private String description;

}
