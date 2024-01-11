package com.example.bonapetit.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@Accessors(chain = true)
public class UserEntity extends GenericEntity {

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "addedBy")
    private List<RecipeEntity> addedRecipes;

    @ManyToMany
    @JoinTable(
            name = "user_recipe_favourite",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "recipe_id"))
    private Set<RecipeEntity> favouriteRecipes;

    public void addFavourite(RecipeEntity recipeEntity){
        favouriteRecipes.add(recipeEntity);
    }

    public void deleteFavourite(RecipeEntity recipeEntity){
        favouriteRecipes.remove(recipeEntity);
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "userName='" + username + '\'' +
                ", password='" + (password != null ? "[PROVIDED]" : null) + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
