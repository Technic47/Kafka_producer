package ru.kuznetsov.kafka_producer.scheduler;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.kuznetsov.kafka_producer.service.KafkaService;

import java.util.Random;

@Component
@RequiredArgsConstructor
public class MessageGeneratorScheduler {

    private final KafkaService kafkaService;
    private final Random random = new Random();

    Logger logger = LoggerFactory.getLogger(MessageGeneratorScheduler.class);

    @Scheduled(fixedRate = 60000)
    public void scheduleMessageGenerator() {
        logger.info("Starting scheduled message generator");

        int number = random.nextInt(100);
        kafkaService.sendGeneratorAmount(number);
        logger.info("Send to Generator amount: {}", number);

        logger.info("Finishing scheduled message generator");
    }
}
