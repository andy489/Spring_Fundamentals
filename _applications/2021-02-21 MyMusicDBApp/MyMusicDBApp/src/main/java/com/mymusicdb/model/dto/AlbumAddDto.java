package com.mymusicdb.model.dto;

import com.mymusicdb.model.enumerated.BandEnum;
import com.mymusicdb.model.enumerated.GenreEnum;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Accessors(chain = true)
public class AlbumAddDto {

    @NotBlank
    @Size(min = 3, max = 20, message = "length must be between 3 and 20 characters")
    private String name;

    @NotBlank
    @Size(min = 5, message = "length must be at least 5 characters")
    private String imgUrl;

    @NotNull
    @Positive
    private BigDecimal price;

    @NotNull
    @Min(value = 10, message = "Copies must be more at least 10")
    private Integer copies;
    @NotNull
    @PastOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;

    private String producer;

    @NotNull
    private BandEnum band;

    @NotNull
    private GenreEnum genre;

    @NotBlank
    @Size(min = 5, message = "length must be at least 5 characters")
    private String description;

}
