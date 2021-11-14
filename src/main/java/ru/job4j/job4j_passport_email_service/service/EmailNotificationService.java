package ru.job4j.job4j_passport_email_service.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.job4j.job4j_passport_email_service.kafka.dto.PassportDto;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class EmailNotificationService {

    private ExecutorService pool;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public EmailNotificationService() {
        this.pool = Executors.newFixedThreadPool(
                2
        );
    }

    public void emailTo(PassportDto passportDto) {
        String subject = "Notification " + passportDto.getFio() + " to email " + passportDto.getEmail();
        String body = "Dear, " + passportDto.getFio() + ". Your passport is expired. You have to replace your passport.";
        pool.submit(new Runnable() {
            @Override
            public void run() {
                send(subject, body, passportDto.getEmail());
            }
        });
    }

    private void send(String subject, String body, String email) {
        long delay = getRandomDelay();
        logger.info("Email to {} is sending, delay = {} seconds", email, delay / 1000);
        try {
            Thread.sleep(delay);
            logger.info("Email to {} is sent", email);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private long getRandomDelay() {
        final Random r = new Random();
        return 1000 + r.nextInt(10000);
    }

    public void close() {
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
