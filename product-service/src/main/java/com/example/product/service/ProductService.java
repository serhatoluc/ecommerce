package com.example.product.service;

import com.example.common.dto.product.DecreaseStockRequest;
import com.example.common.dto.product.ProductRequest;
import com.example.common.dto.product.ProductResponse;
import java.util.List;

public interface ProductService {
    List<ProductResponse> findAll();
    ProductResponse findById(Long id);
    ProductResponse create(ProductRequest request);
    ProductResponse update(Long id, ProductRequest request);
    void delete(Long id);
    ProductResponse decreaseStock(Long id, DecreaseStockRequest request);
}
