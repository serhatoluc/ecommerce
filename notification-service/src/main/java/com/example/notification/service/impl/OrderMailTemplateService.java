package com.example.notification.service.impl;

import com.example.common.event.OrderCreatedEvent;
import com.example.notification.service.MailTemplateService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

@Service
public class OrderMailTemplateService implements MailTemplateService {

    private static final String ORDER_CONFIRMATION_SUBJECT = "Sipariş Onayı - Sipariş #%d";

    @Override
    public String generateOrderConfirmationSubject(OrderCreatedEvent event) {
        return String.format(ORDER_CONFIRMATION_SUBJECT, event.getOrderId());
    }

    //TODO: Template'ler DB'de tutulabilir, zamandan kazanmak için servicede bu şekilde yazıldı.

    @Override
    public String generateOrderConfirmationPlainText(OrderCreatedEvent event) {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("tr", "TR"));
        String formattedTotal = currencyFormat.format(event.getTotalPrice());

        return String.format(
            "Sayın %s,\n\n" +
            "Siparişiniz başarıyla alınmıştır.\n\n" +
            "Sipariş Bilgileri:\n" +
            "Sipariş No: #%d\n" +
            "Toplam Tutar: %s\n\n" +
            "Siparişinizin durumunu takip edebilirsiniz.\n\n" +
            "Teşekkür ederiz.",
            event.getCustomerName(),
            event.getOrderId(),
            formattedTotal
        );
    }

    @Override
    public String generateOrderConfirmationHtml(OrderCreatedEvent event) {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("tr", "TR"));
        String formattedTotal = currencyFormat.format(event.getTotalPrice());

        return String.format(
            "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <style>\n" +
            "        body { font-family: Arial, sans-serif; line-height: 1.6; color: #333; }\n" +
            "        .container { max-width: 600px; margin: 0 auto; padding: 20px; }\n" +
            "        .header { background-color: #4CAF50; color: white; padding: 20px; text-align: center; }\n" +
            "        .content { padding: 20px; background-color: #f9f9f9; }\n" +
            "        .order-info { background-color: white; padding: 15px; margin: 15px 0; border-left: 4px solid #4CAF50; }\n" +
            "        .order-detail { margin: 10px 0; }\n" +
            "        .order-id { font-size: 18px; font-weight: bold; color: #4CAF50; }\n" +
            "        .total { font-size: 20px; font-weight: bold; color: #333; margin-top: 10px; }\n" +
            "        .footer { text-align: center; padding: 20px; color: #666; font-size: 12px; }\n" +
            "    </style>\n" +
            "</head>\n" +
            "<body>\n" +
            "    <div class=\"container\">\n" +
            "        <div class=\"header\">\n" +
            "            <h1>Sipariş Onayı</h1>\n" +
            "        </div>\n" +
            "        <div class=\"content\">\n" +
            "            <p>Sayın <strong>%s</strong>,</p>\n" +
            "            <p>Siparişiniz başarıyla alınmıştır.</p>\n" +
            "            <div class=\"order-info\">\n" +
            "                <div class=\"order-detail\">\n" +
            "                    <span class=\"order-id\">Sipariş No: #%d</span>\n" +
            "                </div>\n" +
            "                <div class=\"order-detail\">\n" +
            "                    <span class=\"total\">Toplam Tutar: %s</span>\n" +
            "                </div>\n" +
            "            </div>\n" +
            "            <p>Siparişinizin durumunu takip edebilirsiniz.</p>\n" +
            "            <p>Teşekkür ederiz.</p>\n" +
            "        </div>\n" +
            "        <div class=\"footer\">\n" +
            "            <p>Bu bir otomatik e-postadır. Lütfen yanıtlamayın.</p>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "</body>\n" +
            "</html>",
            event.getCustomerName(),
            event.getOrderId(),
            formattedTotal
        );
    }
}

