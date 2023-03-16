package com.mymusicdb.mapper;

import com.mymusicdb.model.dto.AlbumAddDto;
import com.mymusicdb.model.dto.UserRegisterDto;
import com.mymusicdb.model.entity.AlbumEntity;
import com.mymusicdb.model.entity.UserEntity;
import com.mymusicdb.model.view.AlbumView;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MapStructMapper {

    UserEntity toUserEntity(UserRegisterDto userRegisterDto);

    AlbumEntity toAlbumEntity(AlbumAddDto albumAddDto);

    AlbumView toAlbumView(AlbumEntity albumEntity);

    default AlbumView toAlbumViewFull(AlbumEntity albumEntity) {
        AlbumView albumView = this.toAlbumView(albumEntity);

        albumView.setBand(albumEntity.getArtist().getBand());

        return albumView;
    }
}