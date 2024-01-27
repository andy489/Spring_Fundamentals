package com.shoppinglist.service;

import com.shoppinglist.model.entity.CategoryEntity;
import com.shoppinglist.model.enumerated.CategoryEnum;
import com.shoppinglist.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {

        this.categoryRepository = categoryRepository;
    }

    public CategoryEntity getByName(CategoryEnum name) {
        return categoryRepository.findByName(name).orElseThrow(NoSuchElementException::new);
    }

}
