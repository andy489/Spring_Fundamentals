package com.mymusicdb.repository;

import com.mymusicdb.model.entity.ArtistEntity;
import com.mymusicdb.model.enumerated.BandEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArtistRepository extends JpaRepository<ArtistEntity, Long> {

    Optional<ArtistEntity> findByBand(BandEnum band);

}
