package ru.kuznetsov.kafka_producer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.kuznetsov.kafka_producer.dto.MessageDto;

@Service
public class MessageService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public boolean sendMessage(MessageDto message) {
        try {
            kafkaTemplate.send(message.getTopic(), message.getMessage());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
