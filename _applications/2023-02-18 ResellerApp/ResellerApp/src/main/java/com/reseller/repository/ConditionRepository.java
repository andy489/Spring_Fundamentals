package com.reseller.repository;

import com.reseller.model.entity.ConditionEntity;
import com.reseller.model.entity.enums.ConditionName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConditionRepository extends JpaRepository<ConditionEntity, Long> {
    ConditionEntity findByConditionName(ConditionName name);
}
