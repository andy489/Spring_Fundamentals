package com.shoppinglist.repository;

import com.shoppinglist.model.entity.ProductEntity;
import com.shoppinglist.model.enumerated.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    Set<ProductEntity> findByCategoryName(CategoryEnum category);

}
