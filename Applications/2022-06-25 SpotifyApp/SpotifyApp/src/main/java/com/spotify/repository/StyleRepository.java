package com.spotify.repository;

import com.spotify.model.entity.StyleEntity;
import com.spotify.model.enumerated.StyleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StyleRepository extends JpaRepository<StyleEntity, Long> {

    Optional<StyleEntity> findByName(StyleEnum name);

}
