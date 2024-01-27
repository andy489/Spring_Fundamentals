package com.example.bonapetit.model.entity;


import com.example.bonapetit.model.enumerated.CategoryEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Entity
@Table(name = "categories")
@Getter
@Setter
@Accessors(chain = true)
public class CategoryEntity extends GenericEntity {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CategoryEnum name;

    @Column(nullable = false)
    @Lob
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "id")
    private List<RecipeEntity> recipes;
}
