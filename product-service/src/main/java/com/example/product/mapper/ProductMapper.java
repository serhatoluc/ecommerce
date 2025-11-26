package com.example.product.mapper;

import com.example.common.dto.product.ProductResponse;
import com.example.product.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {
    ProductResponse toResponse(Product product);
}

