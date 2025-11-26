package com.example.notification.consumer;

import com.example.common.event.OrderCreatedEvent;
import com.example.notification.service.MailService;
import com.example.notification.service.MailTemplateService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderCreatedConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderCreatedConsumer.class);

    private final MailService mailService;
    private final MailTemplateService mailTemplateService;

    @KafkaListener(topics = "order-created", groupId = "notification-service", containerFactory = "orderKafkaListenerContainerFactory")
    public void consume(OrderCreatedEvent event) {
        try {
            String subject = mailTemplateService.generateOrderConfirmationSubject(event);
            String plainText = mailTemplateService.generateOrderConfirmationPlainText(event);
            String html = mailTemplateService.generateOrderConfirmationHtml(event);

            mailService.sendEmail(event.getEmail(), subject, plainText, html);
            LOGGER.info("Order confirmation email sent successfully to {} for order #{}", event.getEmail(), event.getOrderId());
        } catch (Exception e) {
            LOGGER.error("Failed to send order confirmation email to {} for order #{}: {}", 
                event.getEmail(), event.getOrderId(), e.getMessage(), e);
        }
    }
}
