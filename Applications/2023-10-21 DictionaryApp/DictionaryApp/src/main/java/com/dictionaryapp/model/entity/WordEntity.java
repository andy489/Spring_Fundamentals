package com.dictionaryapp.model.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

@Entity
@Table(name = "words")
@Setter
@Getter
@Accessors(chain = true)
public class WordEntity extends GenericEntity {

    @Column(nullable = false)
    private String term;

    @Column(nullable = false)
    private String translation;

    private String example;

    @Column(nullable = false)
    private Date inputDate;

    @JoinColumn(nullable = false)
    @ManyToOne
    private LanguageEntity language;

    @ManyToOne
    private UserEntity addedBy;
}
