package com.shoppinglist.mapper;

import com.shoppinglist.model.dto.ProductAddDto;
import com.shoppinglist.model.dto.UserRegisterDto;
import com.shoppinglist.model.entity.ProductEntity;
import com.shoppinglist.model.entity.UserEntity;
import com.shoppinglist.model.view.ProductView;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MapStructMapper {

    UserEntity toUserEntity(UserRegisterDto userRegisterDto);


    ProductEntity toProductEntity(ProductAddDto songAddDto);

    //    @Mapping(target = "category", ignore = true)
    //    @Mapping(source = "", constant = "")
    //    @Mapping(source = "", target = "")
    ProductView toProductView(ProductEntity productEntity);

}

