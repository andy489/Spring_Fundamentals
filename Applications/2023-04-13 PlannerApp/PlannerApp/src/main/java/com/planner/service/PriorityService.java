package com.planner.service;

import com.planner.model.entity.PriorityEntity;
import com.planner.model.enumerated.PriorityEnum;
import com.planner.repository.PriorityRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class PriorityService {

    private final PriorityRepository priorityRepository;

    public PriorityService(PriorityRepository priorityRepository) {
        this.priorityRepository = priorityRepository;
    }

    public PriorityEntity getByName(PriorityEnum name) {
        return priorityRepository.findByName(name).orElseThrow(NoSuchElementException::new);
    }

}
