package com.example.order.feign;

import com.example.common.dto.product.DecreaseStockRequest;
import com.example.common.dto.product.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "productClient", url = "${clients.product.url}")
public interface ProductClient {

    @GetMapping("/products/{id}")
    ProductResponse getProduct(@PathVariable("id") Long id);

    @PostMapping("/products/{id}/decreaseStock")
    ProductResponse decreaseStock(@PathVariable("id") Long id, @RequestBody DecreaseStockRequest request);
}
