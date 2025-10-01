package ru.kuznetsov.kafka_producer.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class GeneratorService {

    private final static int DEFAULT_SCHEDULE_TIME_MILLIS = 1000;
    private final List<ScheduledFuture> taskList = new ArrayList<>();

    private final KafkaService kafkaService;
    private final Random random = new Random();
    private final ScheduledExecutorService executor;

    Logger logger = LoggerFactory.getLogger(GeneratorService.class);

    public void sendGeneratorAmount(Integer amount) {
        kafkaService.sendGeneratorAmount(amount);
    }

    public Integer sendGeneratorRandomAmount() {
        int number = random.nextInt(100);
        sendGeneratorAmount(number);
        return number;
    }

    public void setUpConcurrentGeneration(boolean generation, Integer threads, Integer initialDelay, Integer period) {
        if (generation) {
            logger.info("Concurrent Generation started");
            threads = threads == null ? 10 : threads;
            initialDelay = initialDelay == null ? 0 : initialDelay;
            period = period == null ? DEFAULT_SCHEDULE_TIME_MILLIS : period;

            for (int i = 0; i < threads; i++) {
                taskList.add(executor.scheduleAtFixedRate(getGenerationThread(), initialDelay, period, TimeUnit.MILLISECONDS));
            }
        } else {
            taskList.forEach(task -> task.cancel(false));
            logger.warn("Concurrent Generation stopped");
        }

    }

    private Runnable getGenerationThread() {
        return () -> sendGeneratorAmount(random.nextInt(10));
    }
}
