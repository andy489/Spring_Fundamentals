package com.reseller.service;

import com.reseller.model.entity.ConditionEntity;
import com.reseller.model.entity.enums.ConditionName;
import com.reseller.repository.ConditionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConditionService {
    private final ConditionRepository conditionRepository;

    @Autowired
    public ConditionService(ConditionRepository conditionRepository) {
        this.conditionRepository = conditionRepository;
    }


    public ConditionEntity getByConditionName(ConditionName name) {
        return conditionRepository.findByConditionName(name);
    }
}
