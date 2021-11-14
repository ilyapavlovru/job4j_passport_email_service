package ru.job4j.job4j_passport_email_service.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import ru.job4j.job4j_passport_email_service.kafka.dto.PassportDto;

import java.io.IOException;

@Component
@EnableKafka
public class KafkaMsgListener {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @KafkaListener(topics = "${app.topic.name}", groupId = "${app.group-id}", containerFactory = "kafkaListenerContainerFactory")
    public void consumeUserMessage(@Payload PassportDto msg, @Headers MessageHeaders headers) throws IOException {
        logger.info("received data in KafkaMsgListener = {}", msg);
    }
}
