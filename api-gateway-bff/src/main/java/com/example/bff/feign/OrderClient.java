package com.example.bff.feign;

import com.example.common.dto.order.OrderRequest;
import com.example.common.dto.order.OrderResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "orderClient", url = "${clients.order.url}")
public interface OrderClient {

    @PostMapping("/orders")
    OrderResponse createOrder(@RequestBody OrderRequest request);

    @GetMapping("/orders/{id}")
    OrderResponse getOrder(@PathVariable("id") Long id);
}
