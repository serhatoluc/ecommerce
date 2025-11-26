package com.example.order.feign;

import com.example.common.dto.customer.CustomerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customerClient", url = "${clients.customer.url}")
public interface CustomerClient {

    @GetMapping("/customers/{id}")
    CustomerResponse getCustomer(@PathVariable("id") Long id);
}
