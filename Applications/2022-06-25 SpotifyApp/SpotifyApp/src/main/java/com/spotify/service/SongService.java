package com.spotify.service;

import com.spotify.mapper.MapStructMapper;
import com.spotify.model.dto.SongAddDto;
import com.spotify.model.entity.SongEntity;
import com.spotify.model.enumerated.StyleEnum;
import com.spotify.model.view.SongsWithTotalDurationView;
import com.spotify.repository.SongRepository;
import com.spotify.model.view.SongView;
import com.spotify.model.view.SongsByGenreView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class SongService {
    private final SongRepository songRepository;
    private final MapStructMapper mapper;
    private final UserService userService;
    private final StyleService styleService;

    @Autowired
    public SongService(
            SongRepository songRepository,
            MapStructMapper mapper,
            UserService userService,
            StyleService styleService
    ) {

        this.songRepository = songRepository;
        this.mapper = mapper;
        this.userService = userService;
        this.styleService = styleService;
    }

    public void addSong(SongAddDto songAddDto) {
        SongEntity songEntity = mapper.toSongEntity(songAddDto);

        songEntity.setStyle(styleService.getByName(songAddDto.getStyle()));

        songRepository.saveAndFlush(songEntity);
    }

    public List<SongView> getSongsByStyle(StyleEnum style) {
        return songRepository.findByStyleName(style)
                .stream()
                .map(mapper::toSongView)
                .sorted(Comparator.comparing(SongView::getTitle))
                .toList();
    }

    public SongsByGenreView getSongsByGenre() {
        EnumMap<StyleEnum, List<SongView>> songsEnumMap = new EnumMap<>(StyleEnum.class);

        Arrays.stream(StyleEnum.values()).forEach(
                style -> songsEnumMap.put(style, getSongsByStyle(style))
        );

        return new SongsByGenreView().setSongsEnumMap(songsEnumMap);
    }

    public void addSongToPlaylist(Long songId) {
        SongEntity songToAdd = songRepository.findById(songId).orElseThrow(NoSuchElementException::new);

        userService.addSongToUser(songToAdd);
    }

    public SongsWithTotalDurationView getMyPlaylist(Long currUserId) {
        List<SongView> mySongs = songRepository.findAllByUserId(currUserId)
                .stream()
                .map(mapper::toSongView)
                .sorted(Comparator.comparing(SongView::getTitle))
                .toList();

        return new SongsWithTotalDurationView(mySongs);
    }

    public void removeSongToPlaylist(Long songId) {
        userService.removeSongFromUser(songId);
    }

}
