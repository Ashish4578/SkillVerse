package com.skillverse.notificationservice.config;

import com.skillverse.notificationservice.model.EnrollmentEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DeadLetterConsumer {

    @KafkaListener(
            topics = "enrollment-events.DLT",
            groupId = "notification-dlt-group"
    )
    public void consumeDLT(EnrollmentEvent event) {

        log.error("💀 Message moved to DLQ: {}", event);

        // TODO:
        // - store in DB
        // - alert system
        // - retry later manually
    }
}