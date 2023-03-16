package com.battleships.service;

import com.battleships.domain.entity.CategoryEntity;

import com.battleships.domain.entity.enums.ShipType;
import com.battleships.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryEntity getByShipType(ShipType name) {
        return categoryRepository.findByName(name);
    }

}
