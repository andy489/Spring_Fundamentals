package com.spotifly.mapper;

import com.spotifly.model.dto.SongAddDto;
import com.spotifly.model.dto.UserRegisterDto;
import com.spotifly.model.entity.SongEntity;
import com.spotifly.model.entity.UserEntity;
import com.spotifly.model.view.SongView;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapStructMapper {

    UserEntity toUserEntity(UserRegisterDto userRegisterDto);

    SongEntity toSongEntity(SongAddDto songAddDto);

    //    @Mapping(target = "", ignore = true)
    //    @Mapping(source = "", constant = "")
    //    @Mapping(source = "", target = "")
    SongView toSongView(SongEntity songEntity);

}