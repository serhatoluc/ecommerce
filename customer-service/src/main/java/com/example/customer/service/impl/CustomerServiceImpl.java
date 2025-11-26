package com.example.customer.service.impl;

import com.example.common.dto.customer.CustomerRequest;
import com.example.common.dto.customer.CustomerResponse;
import com.example.customer.entity.Customer;
import com.example.customer.exception.ErrorMessages;
import com.example.customer.exception.NotFoundException;
import com.example.customer.mapper.CustomerMapper;
import com.example.customer.repository.CustomerRepository;
import com.example.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;
    private final CustomerMapper customerMapper;

    @Override
    public Page<CustomerResponse> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(customerMapper::toResponse);
    }

    @Override
    public CustomerResponse findById(Long id) {
        Customer customer = repository.findById(id).orElseThrow(() -> new NotFoundException(ErrorMessages.CUSTOMER_NOT_FOUND));
        return customerMapper.toResponse(customer);
    }

    @Override
    public CustomerResponse findByEmail(String email) {
        Customer customer = repository.findByEmail(email).orElseThrow(() -> new NotFoundException(ErrorMessages.CUSTOMER_NOT_FOUND));
        return customerMapper.toResponse(customer);
    }

    @Override
    @Transactional
    public CustomerResponse create(CustomerRequest request) {
        Customer customer = Customer.builder().name(request.getName()).email(request.getEmail()).build();
        return customerMapper.toResponse(repository.save(customer));
    }

    @Override
    @Transactional
    public CustomerResponse update(Long id, CustomerRequest request) {
        Customer customer = repository.findById(id).orElseThrow(() -> new NotFoundException(ErrorMessages.CUSTOMER_NOT_FOUND));
        customer.setName(request.getName());
        customer.setEmail(request.getEmail());
        return customerMapper.toResponse(repository.save(customer));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException(ErrorMessages.CUSTOMER_NOT_FOUND);
        }
        repository.deleteById(id);
    }
}
