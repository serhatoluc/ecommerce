package com.example.order.mapper;

import com.example.common.dto.order.OrderRequest;
import com.example.common.dto.order.OrderResponse;
import com.example.order.entity.Order;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = OrderItemMapper.class)
public interface OrderMapper {
    OrderResponse toResponse(Order order);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "totalPrice", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "items", ignore = true)
    Order toEntity(OrderRequest request);

    @AfterMapping
    default void setItemsIfNull(@MappingTarget Order order) {
        if (order.getItems() == null) {
            order.setItems(new java.util.ArrayList<>());
        }
    }
}

