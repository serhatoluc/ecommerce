package com.example.customer.service;

import com.example.common.dto.customer.CustomerRequest;
import com.example.common.dto.customer.CustomerResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {
    Page<CustomerResponse> findAll(Pageable pageable);
    CustomerResponse findById(Long id);
    CustomerResponse findByEmail(String email);
    CustomerResponse create(CustomerRequest request);
    CustomerResponse update(Long id, CustomerRequest request);
    void delete(Long id);
}
