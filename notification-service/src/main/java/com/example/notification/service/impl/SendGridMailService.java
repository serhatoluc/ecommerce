package com.example.notification.service.impl;

import com.example.notification.service.MailService;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class SendGridMailService implements MailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SendGridMailService.class);

    @Value("${sendgrid.api-key}")
    private String apiKey;

    @Value("${sendgrid.from-email}")
    private String fromEmail;

    @Value("${sendgrid.from-name:}")
    private String fromName;

    @Override
    public void sendEmail(String to, String subject, String plainText, String html) {
        try {
            Email from;
            if (fromName != null && !fromName.isEmpty()) {
                from = new Email(fromEmail, fromName);
            } else {
                from = new Email(fromEmail);
            }
            Email toEmail = new Email(to);
            
            Mail mail = new Mail();
            mail.setFrom(from);
            mail.setSubject(subject);
            mail.addContent(new Content("text/plain", plainText));
            if (html != null && !html.isEmpty()) {
                mail.addContent(new Content("text/html", html));
            }
            
            com.sendgrid.helpers.mail.objects.Personalization personalization = new com.sendgrid.helpers.mail.objects.Personalization();
            personalization.addTo(toEmail);
            mail.addPersonalization(personalization);

            SendGrid sg = new SendGrid(apiKey);
            Request request = new Request();
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());

            Response response = sg.api(request);
            LOGGER.info("Email sent to {}: Status Code: {}", to, response.getStatusCode());
        } catch (IOException e) {
            LOGGER.error("Error sending email to {}: {}", to, e.getMessage(), e);
            throw new RuntimeException("Failed to send email", e);
        }
    }

    @Override
    public void sendEmail(String to, String subject, String message) {
        sendEmail(to, subject, message, null);
    }
}

