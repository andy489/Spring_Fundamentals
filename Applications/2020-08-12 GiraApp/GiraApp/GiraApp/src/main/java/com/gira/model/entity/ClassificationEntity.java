package com.gira.model.entity;

import com.gira.model.enumerated.ClassificationEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Entity(name = "uniqueClassificationEntity")
@Table(name = "classifications")
@Getter
@Setter
@Accessors(chain = true)
@ToString
public class ClassificationEntity extends GenericEntity {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ClassificationEnum name;

    @Column(nullable = false)
    private String description;

}
