package com.spotifly.repository;

import com.spotifly.model.entity.StyleEntity;
import com.spotifly.model.enumerated.StyleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StyleRepository extends JpaRepository<StyleEntity, Long> {

    Optional<StyleEntity> findByName(StyleEnum name);

}
