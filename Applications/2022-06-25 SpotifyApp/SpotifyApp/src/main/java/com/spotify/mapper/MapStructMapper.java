package com.spotify.mapper;

import com.spotify.model.dto.SongAddDto;
import com.spotify.model.dto.UserRegisterDto;
import com.spotify.model.entity.SongEntity;
import com.spotify.model.entity.UserEntity;
import com.spotify.model.view.SongView;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MapStructMapper {

    UserEntity toUserEntity(UserRegisterDto userRegisterDto);

    SongEntity toSongEntity(SongAddDto songAddDto);

    //    @Mapping(target = "", ignore = true)
    //    @Mapping(source = "", constant = "")
    //    @Mapping(source = "", target = "")
    SongView toSongView(SongEntity songEntity);

}