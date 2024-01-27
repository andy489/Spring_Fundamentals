package com.example.bonapetit.repo;

import com.example.bonapetit.model.entity.CategoryEntity;
import com.example.bonapetit.model.enumerated.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    Optional<CategoryEntity> findByName(CategoryEnum name);

}
