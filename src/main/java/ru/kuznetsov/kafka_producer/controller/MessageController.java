package ru.kuznetsov.kafka_producer.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kuznetsov.kafka_producer.dto.MessageDto;
import ru.kuznetsov.kafka_producer.service.KafkaService;
import ru.kuznetsov.kafka_producer.service.MessageService;

@RestController
@RequestMapping("message")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;
    private final KafkaService kafkaService;

    Logger logger = LoggerFactory.getLogger(MessageController.class);

    @PostMapping
    ResponseEntity<Boolean> sendMessage(@RequestBody MessageDto message) {
        logger.info("Sending a message to kafka");
        return ResponseEntity.ok(messageService.sendMessage(message));
    }

    @GetMapping("/generate/{count}")
    ResponseEntity<String> generateMessages(@PathVariable Integer count){
        logger.info("Generating {} Messages", count);
        kafkaService.sendGeneratorAmount(count);
        return ResponseEntity.ok(count + " messages sent for generation successfully");
    }
}
