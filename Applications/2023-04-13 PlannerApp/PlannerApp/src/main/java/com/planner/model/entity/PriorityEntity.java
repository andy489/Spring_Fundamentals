package com.planner.model.entity;

import com.planner.model.enumerated.PriorityEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Entity(name = "uniquePriorityEntity")
@Table(name = "priorities")
@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PriorityEntity extends GenericEntity{

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PriorityEnum name;

    @Column(nullable = false)
    private String description;
}
