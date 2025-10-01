package ru.kuznetsov.kafka_producer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.concurrent.*;

@Configuration
@EnableScheduling
public class SpringConfig {

    @Bean
    public ScheduledExecutorService getExecutor(){
        return Executors.newScheduledThreadPool(10);
    }
}
