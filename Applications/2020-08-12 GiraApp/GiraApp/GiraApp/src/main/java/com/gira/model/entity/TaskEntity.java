package com.gira.model.entity;

import com.gira.model.enumerated.ProgressEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

@Entity(name = "uniqueTaskEntity")
@Table(name = "tasks")
@Getter
@Setter
@Accessors(chain = true)
@ToString
public class TaskEntity extends GenericEntity {

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProgressEnum progress;

    @Column(nullable = false)
    private Date dueDate;

    @ManyToOne
    private ClassificationEntity classification;

    @ManyToOne
    private UserEntity assignedTo;

}
