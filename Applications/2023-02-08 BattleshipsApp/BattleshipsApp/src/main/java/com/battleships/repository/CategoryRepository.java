package com.battleships.repository;

import com.battleships.domain.entity.CategoryEntity;
import com.battleships.domain.enumerated.ShipType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    CategoryEntity findByName(ShipType name);

}
