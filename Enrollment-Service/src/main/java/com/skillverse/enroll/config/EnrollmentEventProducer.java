package com.skillverse.enroll.config;

import com.skillverse.enroll.model.EnrollmentEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class EnrollmentEventProducer {

    private final KafkaTemplate<String, EnrollmentEvent> kafkaTemplate;

    private static final String TOPIC = "enrollment-events";

    public void publishEnrollmentEvent(EnrollmentEvent event) {
        log.info("Publishing enrollment event: {}", event);

        kafkaTemplate.send(TOPIC, event)
                .whenComplete((result, ex) -> {
                    if (ex != null) {
                        log.error(" Kafka send failed", ex);
                    } else {
                        log.info(" Kafka send success topic={}, partition={}, offset={}",
                                result.getRecordMetadata().topic(),
                                result.getRecordMetadata().partition(),
                                result.getRecordMetadata().offset());
                    }
                });
    }
}
