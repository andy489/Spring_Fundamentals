package com.planner.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

@Entity(name = "uniqueTaskEntity")
@Table(name = "tasks")
@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TaskEntity extends GenericEntity {

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Date dueDate;

    @ManyToOne
    private PriorityEntity priority;

    @ManyToOne
    private UserEntity assignedTo;

    public void abandon() {
        assignedTo = null;
    }

    public void assignTo(UserEntity assignedTo) {
        this.assignedTo = assignedTo;

    }
}
