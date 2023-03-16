package com.likebook.repository;

import com.likebook.model.entity.MoodEntity;
import com.likebook.model.enumerated.MoodEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MoodRepository extends JpaRepository<MoodEntity, Long> {

    Optional<MoodEntity> findByName(MoodEnum mood);
}
