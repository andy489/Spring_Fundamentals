package com.dictionaryapp.model.view;

import com.dictionaryapp.model.enumerated.LanguageEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Accessors(chain = true)
public class WordHomeModel {

    private Map<LanguageEnum, List<WordView>> words;

    private Integer allWordsCount;

    private List<WordView> emptyList = new ArrayList<>();
}
