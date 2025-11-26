package com.example.notification.service;

import com.example.common.event.OrderCreatedEvent;

public interface MailTemplateService {
    String generateOrderConfirmationSubject(OrderCreatedEvent event);
    String generateOrderConfirmationPlainText(OrderCreatedEvent event);
    String generateOrderConfirmationHtml(OrderCreatedEvent event);
}

