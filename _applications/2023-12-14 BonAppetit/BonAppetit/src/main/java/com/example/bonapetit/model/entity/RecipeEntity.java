package com.example.bonapetit.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Entity
@Table(name = "recipes")
@Getter
@Setter
@Accessors(chain = true)
@ToString
public class RecipeEntity extends GenericEntity {

    @Column(nullable = false)
    private String name;

    private String ingredients;

    @ManyToOne
    @JoinColumn(nullable = false, name = "category_id")
    private CategoryEntity category;

    @ManyToOne
    @JoinColumn(nullable = false, name = "user_id")
    private UserEntity addedBy;

}
