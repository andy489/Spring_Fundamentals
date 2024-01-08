package com.dictionaryapp.service;

import com.dictionaryapp.mapper.MapStructMapper;
import com.dictionaryapp.model.dto.WordAddDto;
import com.dictionaryapp.model.entity.WordEntity;
import com.dictionaryapp.model.enumerated.LanguageEnum;
import com.dictionaryapp.model.view.WordHomeModel;
import com.dictionaryapp.model.view.WordView;
import com.dictionaryapp.repository.WordRepository;
import com.dictionaryapp.session.CurrentUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static java.util.stream.Collectors.groupingBy;

@Service
public class WordService {

    private final Logger LOGGER = LoggerFactory.getLogger(WordService.class);

    private final WordRepository wordRepository;

    private final MapStructMapper mapper;

    private final LanguageService languageService;

    private final UserService userService;

    private final CurrentUser currentUser;

    @Autowired
    public WordService(WordRepository wordRepository,
                       MapStructMapper mapper,
                       LanguageService languageService, UserService userService,
                       CurrentUser currentUser) {

        this.wordRepository = wordRepository;
        this.mapper = mapper;
        this.languageService = languageService;
        this.userService = userService;
        this.currentUser = currentUser;
    }

    public void addWord(WordAddDto wordAddDto) {
        WordEntity wordEntity = mapper.toEntity(wordAddDto);

        wordEntity.setLanguage(languageService.getByName(wordAddDto.getLanguage()));
        wordEntity.setAddedBy(userService.getCurrentUserEntity());

        wordRepository.saveAndFlush(wordEntity);
    }

    public WordHomeModel getWordsHomeModel() {

        WordHomeModel wordHomeModel = new WordHomeModel();

        Map<LanguageEnum, List<WordView>> words =
                wordRepository.findAll().stream()
                        .map(w -> mapper.toWordFull(w, w.getAddedBy().getUsername()))
                        .collect(groupingBy(WordView::getLanguage));

        wordHomeModel.setWords(words);
        wordHomeModel.setAllWordsCount(words.values().stream().mapToInt(List::size).sum());

        return wordHomeModel;
    }

    public void removeWord(UUID wordId) {
        if (!currentUser.isLoggedIn()) {
            return;
        }

        Optional<WordEntity> byId = wordRepository.findById(wordId);

        if (byId.isEmpty()) {
            return;
        }

        WordEntity wordEntity = byId.get();

        wordRepository.deleteById(wordId);

        LOGGER.info("User [{}] with id [{}] finished task with id [{}].",
                currentUser.getUsername(), currentUser.getId(), wordEntity.getId());

    }

    public void removeAllWords() {

        if (!currentUser.isLoggedIn()) {
            return;
        }

        wordRepository.deleteAll();

    }
}
