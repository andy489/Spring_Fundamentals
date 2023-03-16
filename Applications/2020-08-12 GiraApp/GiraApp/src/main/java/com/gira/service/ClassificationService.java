package com.gira.service;

import com.gira.model.entity.ClassificationEntity;
import com.gira.model.enumerated.ClassificationEnum;
import com.gira.repository.ClassificationRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ClassificationService {

    private final ClassificationRepository classificationRepository;

    public ClassificationService(ClassificationRepository classificationRepository) {
        this.classificationRepository = classificationRepository;
    }

    public ClassificationEntity getByName(ClassificationEnum name) {
        return classificationRepository.findByName(name).orElseThrow(NoSuchElementException::new);
    }

}
