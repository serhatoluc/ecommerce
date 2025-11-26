package com.example.order.service;

import com.example.common.dto.order.OrderRequest;
import com.example.common.dto.order.OrderResponse;

public interface OrderService {
    OrderResponse create(OrderRequest request);
    OrderResponse findById(Long id);
}
