package com.coffeeshop.service;

import com.coffeeshop.model.entity.CategoryEntity;
import com.coffeeshop.model.enumerated.CategoryEnum;
import com.coffeeshop.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryEntity getByName(CategoryEnum name) {
        return categoryRepository.findByName(name).orElseThrow(NoSuchElementException::new);
    }
}
