package com.example.customer.mapper;

import com.example.common.dto.customer.CustomerResponse;
import com.example.customer.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CustomerMapper {
    CustomerResponse toResponse(Customer customer);
}

