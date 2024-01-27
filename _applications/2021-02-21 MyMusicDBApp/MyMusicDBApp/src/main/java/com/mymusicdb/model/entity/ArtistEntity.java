package com.mymusicdb.model.entity;

import com.mymusicdb.model.enumerated.BandEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity(name = "uniqueArtistEntity")
@Table(name = "artists")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class ArtistEntity extends GenericEntity {

    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private BandEnum band;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String careerInformation;

    public ArtistEntity(Long id, BandEnum band, String careerInformation) {
        super.setId(id);

        this.band = band;
        this.careerInformation = careerInformation;
    }

}
