package com.dictionaryapp.model.dto;

import com.dictionaryapp.model.enumerated.LanguageEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@Accessors(chain = true)
public class WordAddDto {

    @NotBlank
    @Size(min = 2, max = 50, message = "{word.term.size}")
    private String term;

    @NotBlank
    @Size(min = 2, max = 80, message = "{word.translation.size}")
    private String translation;

    @NotBlank
    @Size(min = 2, max = 200, message = "{word.example.size}")
    private String example;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent
    private Date inputDate;

    @NotNull
    private LanguageEnum language;
}
