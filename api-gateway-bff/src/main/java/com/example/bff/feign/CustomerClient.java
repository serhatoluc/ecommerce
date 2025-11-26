package com.example.bff.feign;

import com.example.common.dto.customer.CustomerRequest;
import com.example.common.dto.customer.CustomerResponse;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "customerClient", url = "${clients.customer.url}")
public interface CustomerClient {

    @GetMapping("/customers/{id}")
    CustomerResponse getCustomer(@PathVariable("id") Long id);

    @GetMapping("/customers/email/{email}")
    CustomerResponse getCustomerByEmail(@PathVariable("email") String email);

    @PostMapping("/customers")
    CustomerResponse createCustomer(@RequestBody CustomerRequest request);

    @PutMapping("/customers/{id}")
    CustomerResponse updateCustomer(@PathVariable("id") Long id, @RequestBody CustomerRequest request);

    @DeleteMapping("/customers/{id}")
    void deleteCustomer(@PathVariable("id") Long id);
}
