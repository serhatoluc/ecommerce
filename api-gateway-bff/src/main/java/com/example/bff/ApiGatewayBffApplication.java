package com.example.bff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.example.bff.feign")
public class ApiGatewayBffApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayBffApplication.class, args);
    }
}

