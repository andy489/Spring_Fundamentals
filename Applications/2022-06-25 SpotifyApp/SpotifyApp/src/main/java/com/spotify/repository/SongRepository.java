package com.spotify.repository;

import com.spotify.model.entity.SongEntity;
import com.spotify.model.enumerated.StyleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface SongRepository extends JpaRepository<SongEntity, Long> {
    
    Set<SongEntity> findByStyleName(StyleEnum style);

    @Query("select s, u from songs s join s.users u on u.id = :id")
    Set<SongEntity> findAllByUserId(@Param("id") Long id);

}
