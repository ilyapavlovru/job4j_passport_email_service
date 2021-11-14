package ru.job4j.job4j_passport_email_service.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void sendEmail(String email, String subject, String body) {
        logger.info("send email is working");
    }
}
