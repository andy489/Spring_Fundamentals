package com.example.bonapetit.repo;

import com.example.bonapetit.model.entity.RecipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository extends JpaRepository<RecipeEntity, Long> {

    List<RecipeEntity> findByAddedById(Long currentId);

}
