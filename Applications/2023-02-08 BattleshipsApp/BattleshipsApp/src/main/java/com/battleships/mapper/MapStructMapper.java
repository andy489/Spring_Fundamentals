package com.battleships.mapper;

import com.battleships.domain.dto.ShipAddDto;
import com.battleships.domain.dto.ShipDto;
import com.battleships.domain.dto.UserRegisterDto;
import com.battleships.domain.entity.CategoryEntity;
import com.battleships.domain.entity.ShipEntity;
import com.battleships.domain.entity.UserEntity;
import com.battleships.domain.dto.CategoryDto;
import com.battleships.domain.dto.UserDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MapStructMapper {

    UserEntity toEntity(UserRegisterDto userRegisterDto);
    ShipEntity toEntity(ShipAddDto shipAddDto);

    CategoryDto toDto(CategoryEntity categoryEntity);
    UserDto toDto(UserEntity userEntity);
    List<ShipDto> toDtoList(List<ShipEntity> ships);

//    @Mapping(target = "isActive", constant = "true")
//    @Mapping(source = "engineType", target = "engine")
}
