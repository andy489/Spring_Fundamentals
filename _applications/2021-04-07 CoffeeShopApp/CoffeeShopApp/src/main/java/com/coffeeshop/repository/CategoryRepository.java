package com.coffeeshop.repository;

import com.coffeeshop.model.entity.CategoryEntity;
import com.coffeeshop.model.enumerated.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    Optional<CategoryEntity> findByName(CategoryEnum name);

}
