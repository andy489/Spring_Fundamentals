package com.shoppinglist.repository;

import com.shoppinglist.model.entity.CategoryEntity;
import com.shoppinglist.model.enumerated.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    Optional<CategoryEntity> findByName(CategoryEnum name);

}
