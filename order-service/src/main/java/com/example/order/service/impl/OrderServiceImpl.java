package com.example.order.service.impl;

import com.example.common.dto.customer.CustomerResponse;
import com.example.common.dto.product.DecreaseStockRequest;
import com.example.common.dto.product.ProductResponse;
import com.example.common.dto.order.OrderItemRequest;
import com.example.common.dto.order.OrderRequest;
import com.example.common.dto.order.OrderResponse;
import com.example.order.entity.Order;
import com.example.order.entity.OrderItem;
import com.example.common.event.OrderCreatedEvent;
import java.time.ZonedDateTime;
import com.example.order.exception.ErrorMessages;
import com.example.order.exception.NotFoundException;
import com.example.order.feign.CustomerClient;
import com.example.order.feign.ProductClient;
import com.example.order.mapper.OrderItemMapper;
import com.example.order.mapper.OrderMapper;
import com.example.order.repository.OrderRepository;
import com.example.order.service.OrderService;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {

    private static final String ORDER_CREATED_TOPIC = "order-created";

    private final OrderRepository orderRepository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;

    @Override
    @Transactional
    public OrderResponse create(OrderRequest request) {
        CustomerResponse customer = customerClient.getCustomer(request.getCustomerId());
        Order order = orderMapper.toEntity(request);
        order.setCreatedDate(ZonedDateTime.now());
        
        if (order.getItems() == null) {
            order.setItems(new java.util.ArrayList<>());
        }

        BigDecimal total = BigDecimal.ZERO;
        for (OrderItemRequest itemRequest : request.getItems()) {
            ProductResponse product = productClient.decreaseStock(itemRequest.getProductId(),
                new DecreaseStockRequest(itemRequest.getQuantity()));
            OrderItem orderItem = orderItemMapper.toEntityWithPrice(itemRequest, product.getPrice());
            orderItem.setOrder(order);
            order.getItems().add(orderItem);
            total = total.add(product.getPrice().multiply(BigDecimal.valueOf(itemRequest.getQuantity())));
        }
        order.setTotalPrice(total);
        Order saved = orderRepository.save(order);
        sendMail(customer, saved);
        return orderMapper.toResponse(saved);
    }

    private void sendMail(CustomerResponse customer, Order saved) {
        OrderCreatedEvent event = new OrderCreatedEvent(
            customer.getEmail(),
            customer.getName(),
            saved.getId(),
            saved.getTotalPrice()
        );
        kafkaTemplate.send(ORDER_CREATED_TOPIC, event);
    }

    @Override
    public OrderResponse findById(Long id) {
        Order order = orderRepository.findById(id)
            .orElseThrow(() -> new NotFoundException(ErrorMessages.ORDER_NOT_FOUND));
        return orderMapper.toResponse(order);
    }
}
