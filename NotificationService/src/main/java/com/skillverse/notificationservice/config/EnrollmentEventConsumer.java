package com.skillverse.notificationservice.config;

import com.skillverse.notificationservice.model.EnrollmentEvent;
import com.skillverse.notificationservice.service.EmailService;
import com.skillverse.notificationservice.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class EnrollmentEventConsumer {

    private final EmailService emailService;
    private final NotificationService notificationService;

    @KafkaListener(topics = "enrollment-events", groupId = "notification-group-v3")
    public void consume(EnrollmentEvent event) {

        log.info("📩 Received event: {}", event);

        // Save to DB
        notificationService.saveNotification(
                event.getUserId(),
                event.getCourseId()
        );

        // Send Email
        emailService.sendEnrollmentEmail(
                event.getUserId(),
                event.getCourseId()
        );

        log.info("✅ Notification processed");
    }
}