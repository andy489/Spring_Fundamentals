package com.mymusicdb.configuration;

import com.mymusicdb.model.entity.AlbumEntity;
import com.mymusicdb.model.entity.ArtistEntity;
import com.mymusicdb.model.entity.UserEntity;
import com.mymusicdb.model.enumerated.BandEnum;
import com.mymusicdb.model.enumerated.GenreEnum;
import com.mymusicdb.repository.AlbumRepository;
import com.mymusicdb.repository.ArtistRepository;
import com.mymusicdb.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.stream.IntStream;

@Component
public class DatabasePopulator {

    private final UserRepository userRepository;
    private final ArtistRepository artistRepository;
    private final AlbumRepository albumRepository;
    private final PasswordEncoder encoder;

    @Autowired
    public DatabasePopulator(
            UserRepository userRepository,
            ArtistRepository artistRepository,
            AlbumRepository albumRepository,
            PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.artistRepository = artistRepository;
        this.albumRepository = albumRepository;
        this.encoder = encoder;
    }

    @PostConstruct
    private void populate() {
        String[] usernames = {"pesho", "gosho", "maria", "andy"};
        String[] fullNames = {"Pesho Peshev", "Gosho Goshev", "Maria Pirnareva", "Andrey Stoev"};
        String[] emails = {"pesho@gmail.com", "gosho@gmail.com", "maria@gmail.com", "andy@gmail.com"};
        String password = encoder.encode("1234");

        List<UserEntity> userEntities = IntStream.rangeClosed(1, 4).boxed()
                .map(i -> new UserEntity(Long.parseLong(
                        i.toString()),
                        usernames[i - 1],
                        fullNames[i - 1],
                        emails[i - 1],
                        password
                )).toList();

        userRepository.saveAllAndFlush(userEntities);

        BandEnum[] bands = BandEnum.values();

        List<ArtistEntity> artistEntities = IntStream.rangeClosed(1, bands.length).boxed()
                .map(i -> new ArtistEntity(Long.parseLong(i.toString()),
                        BandEnum.valueOf(bands[i - 1].name()),
                        bands[i - 1].getDescription()
                )).toList();

        if (artistRepository.count() == 0) {
            artistRepository.saveAllAndFlush(artistEntities);
        }

        String[] names = {"Ride the Lightning", "The Works", "Madonna Anthology", "S&M2"};
        String[] imgUrl = {"01-RideTheLightning", "02-TheWorks", "03-MadonnaAnthology", "04-S&M2"};
        String[] description = {
                "Ride the Lightning is the second studio album by American heavy metal band Metallica, released on July 27, 1984, by the independent record label Megaforce Records. The album was recorded in three weeks with producer Flemming Rasmussen at Sweet Silence Studios in Copenhagen, Denmark. The artwork, based on a concept by the band, depicts an electric chair being struck by lightning flowing from the band logo. The title was taken from a passage in Stephen King's novel The Stand, in which a character uses the phrase to refer to execution by electric chair.",
                "The Works is the eleventh studio album by the British rock band Queen. It was released on 27 February 1984 by EMI Records just shortly after recording for the album had been completed in the United Kingdom and it is the band's first studio album to be released by Capitol Records in the United States. After the synth-heavy Hot Space (1982), the album saw the re-emergence of Brian May and Roger Taylor's rock sound, while still incorporating the early 80s retro futuristic electronic music (Freddie Mercury) and New York funk scenes (John Deacon). Recorded at the Record Plant Studios in Los Angeles, California, and Musicland Studios in Munich, Germany, from August 1983 to January 1984, the album's title comes from a comment Taylor made as recording began – \"Let's give them the works!\".",
                "American singer Madonna has released 14 studio albums, three soundtrack albums, six live albums, seven compilation albums, and 38 other limited releases. Recognized as the world's best-selling female recording artist of all time by the Guinness World Records, Madonna has accumulated a total record sales of more than 300 million units worldwide. In their 2006 press release, the International Federation of the Phonographic Industry (IFPI) confirmed that Madonna's albums alone had sold over 200 million copies worldwide. She is ranked by the RIAA as the best-selling female rock artist of the 20th century and third highest-certified female artist in the United States, with 64.5 million album units. She holds the all-time record for the most number-one albums by a female artist in major music markets such as Australia, Germany, and the United Kingdom.",
                "S&M2 (stylized as S&M2) is a live album by American heavy metal band Metallica and the San Francisco Symphony. It is a follow-up to S&M, a live collaborative album released in 1999. The album was recorded during a live performance in San Francisco at the Chase Center in 2019. The performance was also filmed and released theatrically on October 9, 2019."
        };

        GenreEnum[] genres = {GenreEnum.METAL, GenreEnum.POP, GenreEnum.POP, GenreEnum.METAL};
        BigDecimal[] prices = {BigDecimal.valueOf(29), BigDecimal.valueOf(28), BigDecimal.valueOf(22), BigDecimal.valueOf(19)};
        LocalDate[] releaseDates = {
                LocalDate.of(1984, 7, 27),
                LocalDate.of(1984, Calendar.MARCH, 27),
                LocalDate.of(2019, Calendar.AUGUST, 18),
                LocalDate.of(2020, Calendar.OCTOBER, 28)
        };
        Integer[] copies = {2500000, 2000000, 500000, 237199};
        String[] producers = {
                "Flemming RasmussenMark Whitaker",
                "Queen,Reinhold Mack",
                null,
                "Greg Fidelman, James Hetfield, Lars Ulrich"
        };

        List<AlbumEntity> albumEntities = IntStream.rangeClosed(1, names.length).boxed()
                .map(i -> new AlbumEntity(Long.parseLong(i.toString()),
                        names[i - 1],
                        imgUrl[i - 1],
                        description[i - 1],
                        copies[i - 1],
                        prices[i - 1],
                        releaseDates[i - 1],
                        producers[i - 1],
                        genres[i - 1],
                        artistEntities.get((i - 1) % 3),
                        userEntities.get((i - 1) % 3)
                )).toList();

        albumRepository.saveAllAndFlush(albumEntities);
    }

}
