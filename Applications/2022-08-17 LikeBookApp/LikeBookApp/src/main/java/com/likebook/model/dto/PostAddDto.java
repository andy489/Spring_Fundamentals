package com.likebook.model.dto;

import com.likebook.model.enumerated.MoodEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class PostAddDto {

    @NotBlank
    @Size(min = 5, max = 150, message = "length must be between 2 and 50 characters")
    private String content;

    @NotNull
    private MoodEnum mood;

}
