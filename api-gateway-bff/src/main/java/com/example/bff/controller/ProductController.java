package com.example.bff.controller;

import com.example.common.dto.product.ProductResponse;
import com.example.bff.service.ProductBffService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductBffService productBffService;

    @GetMapping
    public List<ProductResponse> getProducts() {
        return productBffService.fetchProducts();
    }
}

