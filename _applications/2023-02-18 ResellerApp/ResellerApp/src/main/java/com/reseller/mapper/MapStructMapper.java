package com.reseller.mapper;

import com.reseller.model.dto.OfferAddDto;
import com.reseller.model.view.OfferDto;
import com.reseller.model.dto.UserRegisterDto;
import com.reseller.model.entity.OfferEntity;
import com.reseller.model.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapStructMapper {

    UserEntity toEntity(UserRegisterDto userRegisterDto);

    OfferEntity toEntity(OfferAddDto offerAddDto);

    default OfferDto toDto(OfferEntity offerEntity) {
        OfferDto offerDto = new OfferDto();

        offerDto.setId(offerEntity.getId());
        offerDto.setPrice(offerEntity.getPrice());
        offerDto.setDescription(offerEntity.getDescription());

        offerDto.setConditionName(offerEntity.getCondition().getConditionName().getDisplayName());
        offerDto.setSellerUsername(offerEntity.getOwner().getUsername());

        return offerDto;
    }
}
