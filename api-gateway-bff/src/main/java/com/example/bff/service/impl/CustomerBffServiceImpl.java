package com.example.bff.service.impl;

import com.example.common.dto.customer.CustomerRequest;
import com.example.common.dto.customer.CustomerResponse;
import com.example.bff.feign.CustomerClient;
import com.example.bff.service.CustomerBffService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerBffServiceImpl implements CustomerBffService {

    private final CustomerClient customerClient;

    @Override
    public CustomerResponse fetchCustomer(Long id) {
        return customerClient.getCustomer(id);
    }

    @Override
    public CustomerResponse fetchCustomerByEmail(String email) {
        return customerClient.getCustomerByEmail(email);
    }

    @Override
    public CustomerResponse createCustomer(CustomerRequest request) {
        return customerClient.createCustomer(request);
    }

    @Override
    public CustomerResponse updateCustomer(Long id, CustomerRequest request) {
        return customerClient.updateCustomer(id, request);
    }

    @Override
    public void deleteCustomer(Long id) {
        customerClient.deleteCustomer(id);
    }
}

