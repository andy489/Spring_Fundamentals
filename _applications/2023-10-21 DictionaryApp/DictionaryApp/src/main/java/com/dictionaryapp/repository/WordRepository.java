package com.dictionaryapp.repository;

import com.dictionaryapp.model.entity.WordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WordRepository extends JpaRepository<WordEntity, UUID> {
}
