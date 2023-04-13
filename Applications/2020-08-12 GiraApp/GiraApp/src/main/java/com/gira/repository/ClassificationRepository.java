package com.gira.repository;

import com.gira.model.entity.ClassificationEntity;
import com.gira.model.enumerated.ClassificationEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClassificationRepository extends JpaRepository<ClassificationEntity, Long> {

    Optional<ClassificationEntity> findByName(ClassificationEnum name);

}
