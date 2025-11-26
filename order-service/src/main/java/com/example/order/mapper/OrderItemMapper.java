package com.example.order.mapper;

import com.example.common.dto.order.OrderItemRequest;
import com.example.common.dto.order.OrderItemResponse;
import com.example.order.entity.OrderItem;
import java.math.BigDecimal;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderItemMapper {
    OrderItemResponse toResponse(OrderItem orderItem);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "order", ignore = true)
    @Mapping(target = "price", ignore = true)
    OrderItem toEntity(OrderItemRequest request);

    default OrderItem toEntityWithPrice(OrderItemRequest request, BigDecimal price) {
        OrderItem orderItem = toEntity(request);
        orderItem.setPrice(price);
        return orderItem;
    }
}

