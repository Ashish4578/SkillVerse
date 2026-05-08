package com.skillverse.notificationservice.config;

import com.skillverse.notificationservice.model.EnrollmentEvent;
import com.skillverse.notificationservice.service.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MyKafkaConsumer {

    private final NotificationService notificationService;

    @KafkaListener(topics = "enrollment-notify-topic", groupId = "my-group-order", containerFactory = "userCreatedByAuthKafkaListenerFactory")
    public void consumedOrder(EnrollmentEvent enrollmentEvent) {
        notificationService.saveNotification(enrollmentEvent.getUserId(),  enrollmentEvent.getCourseId());
        System.out.println("Received User Data : " + enrollmentEvent.toString());
    }
}
