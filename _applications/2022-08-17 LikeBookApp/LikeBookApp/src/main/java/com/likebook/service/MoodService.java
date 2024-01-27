package com.likebook.service;

import com.likebook.model.entity.MoodEntity;
import com.likebook.model.enumerated.MoodEnum;
import com.likebook.repository.MoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.NoSuchElementException;

@Service
public class MoodService {

    private final MoodRepository moodRepository;

    @Autowired
    public MoodService(MoodRepository moodRepository) {
        this.moodRepository = moodRepository;
    }

    public MoodEntity getByName(MoodEnum mood) {
        return moodRepository.findByName(mood).orElseThrow(NoSuchElementException::new);
    }

    public void initMoods() {
        if (moodRepository.count() > 0) {
            return;
        }

        Arrays.stream(MoodEnum.values())
                .forEach(m -> {
                    MoodEntity moodEntity = new MoodEntity();

                    moodEntity.setName(m);
                    moodEntity.setDescription(m.getDescription());

                    moodRepository.saveAndFlush(moodEntity);
                });
    }

}
