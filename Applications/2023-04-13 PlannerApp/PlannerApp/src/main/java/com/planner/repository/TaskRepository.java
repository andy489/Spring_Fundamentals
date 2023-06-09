package com.planner.repository;

import com.planner.model.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

    Optional<TaskEntity> findById(Long id);

    List<TaskEntity> findByAssignedToId(Long currentId);
    List<TaskEntity> findByAssignedToIdIsNull();

}
