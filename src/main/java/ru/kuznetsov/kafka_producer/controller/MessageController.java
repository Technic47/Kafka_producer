package ru.kuznetsov.kafka_producer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kuznetsov.kafka_producer.dto.MessageDto;
import ru.kuznetsov.kafka_producer.service.MessageService;

@RestController
@RequestMapping("message")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping
    ResponseEntity<Boolean> sendMessage(@RequestBody MessageDto message) {
        return ResponseEntity.ok(messageService.sendMessage(message));
    }
}
