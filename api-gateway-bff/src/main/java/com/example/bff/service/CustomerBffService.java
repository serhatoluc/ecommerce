package com.example.bff.service;

import com.example.common.dto.customer.CustomerRequest;
import com.example.common.dto.customer.CustomerResponse;
import java.util.List;

public interface CustomerBffService {

    CustomerResponse fetchCustomer(Long id);
    CustomerResponse fetchCustomerByEmail(String email);
    CustomerResponse createCustomer(CustomerRequest request);
    CustomerResponse updateCustomer(Long id, CustomerRequest request);
    void deleteCustomer(Long id);
}

