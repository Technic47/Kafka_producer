package ru.kuznetsov.kafka_producer.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageDto {
    private String topic;
    private String message;
}
