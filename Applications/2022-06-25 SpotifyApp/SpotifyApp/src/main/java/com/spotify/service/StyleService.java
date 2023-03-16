package com.spotify.service;

import com.spotify.model.entity.StyleEntity;
import com.spotify.model.enumerated.StyleEnum;
import com.spotify.repository.StyleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class StyleService {

    private final StyleRepository styleRepository;

    @Autowired
    public StyleService(StyleRepository styleRepository) {


        this.styleRepository = styleRepository;
    }

    public StyleEntity getByName(StyleEnum name) {
        return styleRepository.findByName(name).orElseThrow(NoSuchElementException::new);
    }

}
