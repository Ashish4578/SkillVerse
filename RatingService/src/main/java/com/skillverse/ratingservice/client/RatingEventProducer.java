package com.skillverse.ratingservice.client;

import com.skillverse.ratingservice.entity.RatingCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class RatingEventProducer {

    private final KafkaTemplate<String, RatingCreatedEvent> kafkaTemplate;

    private static final String TOPIC = "rating-created";

    public void publish(RatingCreatedEvent event) {

        log.info("Publishing rating event: {}", event);

        kafkaTemplate.send(TOPIC, event)
                .whenComplete((result, ex) -> {
                    if (ex != null) {
                        log.error("Kafka send failed", ex);
                    } else {
                        log.info("Kafka success topic={}, offset={}",
                                result.getRecordMetadata().topic(),
                                result.getRecordMetadata().offset());
                    }
                });
    }
}