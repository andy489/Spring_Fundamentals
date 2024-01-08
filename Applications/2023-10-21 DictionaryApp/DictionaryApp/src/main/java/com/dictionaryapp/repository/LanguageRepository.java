package com.dictionaryapp.repository;

import com.dictionaryapp.model.entity.LanguageEntity;
import com.dictionaryapp.model.enumerated.LanguageEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface LanguageRepository extends JpaRepository<LanguageEntity, UUID> {

    Optional<LanguageEntity> findByLanguageName(LanguageEnum name);
}
