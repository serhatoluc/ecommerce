package com.example.product.service.impl;

import com.example.common.dto.product.DecreaseStockRequest;
import com.example.common.dto.product.ProductRequest;
import com.example.common.dto.product.ProductResponse;
import com.example.product.entity.Product;
import com.example.product.exception.BusinessException;
import com.example.product.exception.ErrorMessages;
import com.example.product.exception.NotFoundException;
import com.example.product.mapper.ProductMapper;
import com.example.product.repository.ProductRepository;
import com.example.product.service.ProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final ProductMapper productMapper;

    @Override
    public List<ProductResponse> findAll() {
        return repository.findAll().stream().map(productMapper::toResponse).toList();
    }

    @Override
    public ProductResponse findById(Long id) {
        return productMapper.toResponse(findEntity(id));
    }

    @Override
    @Transactional
    public ProductResponse create(ProductRequest request) {
        Product product = Product.builder()
            .name(request.getName())
            .price(request.getPrice())
            .stock(request.getStock())
            .build();
        return productMapper.toResponse(repository.save(product));
    }

    @Override
    @Transactional
    public ProductResponse update(Long id, ProductRequest request) {
        Product product = findEntity(id);
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());
        return productMapper.toResponse(repository.save(product));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException(ErrorMessages.PRODUCT_NOT_FOUND);
        }
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public ProductResponse decreaseStock(Long id, DecreaseStockRequest request) {
        Product product = findEntity(id);
        if (product.getStock() < request.getQuantity()) {
            throw new BusinessException(ErrorMessages.INSUFFICIENT_STOCK);
        }
        product.setStock(product.getStock() - request.getQuantity());
        return productMapper.toResponse(repository.save(product));
    }

    private Product findEntity(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(ErrorMessages.PRODUCT_NOT_FOUND));
    }
}
