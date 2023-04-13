package com.planner.repository;

import com.planner.model.entity.PriorityEntity;
import com.planner.model.enumerated.PriorityEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PriorityRepository extends JpaRepository<PriorityEntity, Long> {

    Optional<PriorityEntity> findByName(PriorityEnum name);

}
