package com.mymusicdb.service;

import com.mymusicdb.model.entity.ArtistEntity;
import com.mymusicdb.model.enumerated.BandEnum;
import com.mymusicdb.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ArtistService {

    private final ArtistRepository artistRepository;

    @Autowired
    public ArtistService(ArtistRepository artistRepository) {


        this.artistRepository = artistRepository;
    }

    public ArtistEntity getByBand(BandEnum name) {
        return artistRepository.findByBand(name).orElseThrow(NoSuchElementException::new);
    }

}
