package com.likebook.repository;

import com.likebook.model.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface PostRepository extends JpaRepository<PostEntity, Long> {

    Set<PostEntity> findAllByCreatorId(Long userId);

    Set<PostEntity> findPostsByCreatorIdNot(Long userId);
}
