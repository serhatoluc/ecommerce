package com.example.notification.service;

public interface MailService {
    void sendEmail(String to, String subject, String plainText, String html);
    void sendEmail(String to, String subject, String message);
}

