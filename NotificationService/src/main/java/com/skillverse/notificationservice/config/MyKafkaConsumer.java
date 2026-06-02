package com.skillverse.notificationservice.config;

import com.skillverse.notificationservice.model.EnrollmentEvent;
import com.skillverse.notificationservice.model.UserCreatedEvent;
import com.skillverse.notificationservice.service.EmailService;
import com.skillverse.notificationservice.service.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class MyKafkaConsumer {

    private final NotificationService notificationService;
    private final EmailService emailService;

    @KafkaListener(topics = "enrollment-notify-topic", groupId = "my-group-order", containerFactory = "userCreatedByAuthKafkaListenerFactory")
    public void consumedOrder(EnrollmentEvent enrollmentEvent) {
        notificationService.saveNotification(enrollmentEvent.getUser().getId(),  enrollmentEvent.getCourse().getCourseId());
        emailService.sendEnrollmentEmail(enrollmentEvent);
        log.info("Received Enrollment Data : " + enrollmentEvent.toString());
    }
    @KafkaListener(topics = "user-notify-email-topic", groupId = "my-group-order", containerFactory = "intialAccountCreatedByAuthKafkaListenerFactory")
    public void consumedOrder(UserCreatedEvent userCreatedEvent) {
        emailService.freshAccountCreated(userCreatedEvent);
        log.info("Received User Created Data : " + userCreatedEvent.toString());
    }
}
