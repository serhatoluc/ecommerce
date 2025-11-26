package com.example.bff.feign;

import com.example.common.dto.product.ProductResponse;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "productClient", url = "${clients.product.url}")
public interface ProductClient {

    @GetMapping("/products")
    List<ProductResponse> getProducts();
}
