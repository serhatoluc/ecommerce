package com.example.bff.service.impl;

import com.example.common.dto.product.ProductResponse;
import com.example.bff.feign.ProductClient;
import com.example.bff.service.ProductBffService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductBffServiceImpl implements ProductBffService {

    private final ProductClient productClient;

    @Override
    public List<ProductResponse> fetchProducts() {
        return productClient.getProducts();
    }
}

