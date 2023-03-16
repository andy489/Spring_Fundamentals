package com.coffeeshop.mapper;

import com.coffeeshop.model.dto.OrderAddDto;
import com.coffeeshop.model.dto.UserRegisterDto;
import com.coffeeshop.model.entity.OrderEntity;
import com.coffeeshop.model.entity.UserEntity;
import com.coffeeshop.model.view.OrderView;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MapStructMapper {

    UserEntity toUserEntity(UserRegisterDto userRegisterDto);

    OrderEntity toOrderEntity(OrderAddDto orderAddDto);

    @Mapping(target = "category", ignore = true)
    OrderView toOrderView(OrderEntity orderEntity);


    default OrderView toOrderViewFullMap(OrderEntity orderEntity) {
        OrderView orderView = this.toOrderView(orderEntity);

        orderView.setCategory(orderEntity.getCategory().getName());

        return orderView;
    }
}

