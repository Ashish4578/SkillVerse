package com.skillverse.enroll.config;


import com.skillverse.enroll.model.EnrollmentEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@AllArgsConstructor
@Component
public class MyKafkaProducer {

    private final KafkaTemplate<String, EnrollmentEvent> kafkaTemplate;

    public void sendEnrollCourseNotificationToNotificationService(EnrollmentEvent enrollmentEvent) {
        System.out.println("Sending message to Consumer " + enrollmentEvent);
        kafkaTemplate.send("enrollment-notify-topic", enrollmentEvent);
    }
}
