package com.mymusicdb.model.entity;

import com.mymusicdb.model.enumerated.GenreEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity(name = "albums")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class AlbumEntity extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String imgUrl;

    @Column(columnDefinition = "LONGTEXT")
    private String description;

    @Column(nullable = false)
    private Integer copies;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private LocalDate releaseDate;

    private String producer;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GenreEnum genre;

    @ManyToOne
    private ArtistEntity artist;

    @ManyToOne
    private UserEntity addedFrom;

    public AlbumEntity(
            Long id,
            String name,
            String imgUrl,
            String description,
            Integer copies,
            BigDecimal price,
            LocalDate releaseDate,
            String producer,
            GenreEnum genre,
            ArtistEntity artist,
            UserEntity addedFrom
    ) {
        super.setId(id);

        this.name = name;
        this.imgUrl = imgUrl;
        this.description = description;
        this.copies = copies;
        this.price = price;
        this.releaseDate = releaseDate;
        this.producer = producer;
        this.genre = genre;
        this.artist = artist;
        this.addedFrom = addedFrom;
    }

}
