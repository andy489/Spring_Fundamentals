package com.example.bonapetit.service;

import com.example.bonapetit.model.entity.CategoryEntity;
import com.example.bonapetit.model.enumerated.CategoryEnum;
import com.example.bonapetit.repo.CategoryRepository;
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
