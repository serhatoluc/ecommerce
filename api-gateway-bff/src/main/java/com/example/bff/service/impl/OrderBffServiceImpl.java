package com.example.bff.service.impl;

import com.example.common.dto.order.OrderRequest;
import com.example.common.dto.order.OrderResponse;
import com.example.bff.feign.OrderClient;
import com.example.bff.service.OrderBffService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderBffServiceImpl implements OrderBffService {

    private final OrderClient orderClient;

    @Override
    public OrderResponse createOrder(OrderRequest request) {
        return orderClient.createOrder(request);
    }

    @Override
    public OrderResponse fetchOrder(Long id) {
        return orderClient.getOrder(id);
    }
}

