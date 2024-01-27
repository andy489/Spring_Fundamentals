package com.dictionaryapp.model.view;

import com.dictionaryapp.model.enumerated.LanguageEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Accessors(chain = true)
@ToString
public class WordView {

    private UUID id;

    private String term;

    private String translation;

    private String example;

    private Date inputDate;

    private LanguageEnum language;

    private String addedBy;

}
