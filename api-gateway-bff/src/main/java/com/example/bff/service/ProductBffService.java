package com.example.bff.service;

import com.example.common.dto.product.ProductResponse;
import java.util.List;

public interface ProductBffService {
    List<ProductResponse> fetchProducts();
}

