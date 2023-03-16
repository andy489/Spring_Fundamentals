package com.mymusicdb.model.view;

import com.mymusicdb.model.enumerated.BandEnum;
import com.mymusicdb.model.enumerated.GenreEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Accessors(chain = true)
public class AlbumView {

    private Long id;

    private String name;

    private BandEnum band;

    private GenreEnum genre;

    private BigDecimal price;

    private LocalDate releaseDate;

    private Integer copies;

    private String imgUrl;

}
