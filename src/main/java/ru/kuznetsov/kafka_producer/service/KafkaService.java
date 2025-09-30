package ru.kuznetsov.kafka_producer.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.kuznetsov.kafka_producer.dto.MessageDto;

import static ru.kuznetsov.kafka_producer.config.TopicNames.GENERATOR_TOPIC_NAME;

@Service
@RequiredArgsConstructor
public class KafkaService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    Logger logger = LoggerFactory.getLogger(KafkaService.class);

    public void sendMessage(MessageDto message) {
        logger.info("Sending Message topic: {}, message: {}", message.getTopic(), message.getMessage());
        kafkaTemplate.send(message.getTopic(), message.getMessage());
    }

    public void sendGeneratorAmount(Integer amount){
        logger.info("Sending Generator Amount {}", amount);
        kafkaTemplate.send(GENERATOR_TOPIC_NAME, amount.toString());
    }
}
