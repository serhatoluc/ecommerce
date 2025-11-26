package com.example.bff.service;

import com.example.common.dto.order.OrderRequest;
import com.example.common.dto.order.OrderResponse;

public interface OrderBffService {
    OrderResponse createOrder(OrderRequest request);
    OrderResponse fetchOrder(Long id);
}

