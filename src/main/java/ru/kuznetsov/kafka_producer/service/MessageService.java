package ru.kuznetsov.kafka_producer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kuznetsov.kafka_producer.dto.MessageDto;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final KafkaService kafkaService;

    public boolean sendMessage(MessageDto message) {
        try {
            kafkaService.sendMessage(message);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
