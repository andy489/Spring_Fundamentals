package com.dictionaryapp.model.entity;

import com.dictionaryapp.model.enumerated.LanguageEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Entity
@Table(name = "languages")
@Setter
@Getter
@Accessors(chain = true)
public class LanguageEntity extends GenericEntity {

    @Column(unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private LanguageEnum languageName;

    @Column(nullable = false)
    @Lob
    private String description;

    @OneToMany(mappedBy = "language")
    private List<WordEntity> words;

}
