package com.mymusicdb.service;

import com.mymusicdb.mapper.MapStructMapper;
import com.mymusicdb.model.dto.AlbumAddDto;
import com.mymusicdb.model.entity.AlbumEntity;
import com.mymusicdb.model.view.AlbumView;
import com.mymusicdb.model.view.HomeDataModel;
import com.mymusicdb.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;

@Service
public class AlbumService {
    private final AlbumRepository albumRepository;
    private final MapStructMapper mapper;
    private final UserService userService;
    private final ArtistService artistService;

    @Autowired
    public AlbumService(
            AlbumRepository albumRepository,
            MapStructMapper mapper,
            UserService userService,
            ArtistService artistService
    ) {

        this.albumRepository = albumRepository;
        this.mapper = mapper;
        this.userService = userService;
        this.artistService = artistService;
    }


    public void addAlbum(AlbumAddDto albumAddDto) {
        AlbumEntity albumEntity = mapper.toAlbumEntity(albumAddDto);

        albumEntity.setArtist(artistService.getByBand(albumAddDto.getBand()));
        albumEntity.setAddedFrom(userService.getCurrentUserEntity());

        albumRepository.saveAndFlush(albumEntity);
    }

    public HomeDataModel getData() {

        HomeDataModel homeDataModel = new HomeDataModel();

        homeDataModel.setAlbums(albumRepository.findAll().stream()
                .map(mapper::toAlbumViewFull)
                .sorted(Comparator.comparing(AlbumView::getCopies).reversed())
                .toList()
        );

        return homeDataModel;
    }

    public void removeAlbum(Long albumId) {
        albumRepository.deleteById(albumId);
    }
}
