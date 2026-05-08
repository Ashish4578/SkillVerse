package com.skillverse.userservice.config;

import com.skillverse.userservice.entity.UserCreatedEvent;
import com.skillverse.userservice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MyKafkaConsumer {

    private final UserService userService;

    @KafkaListener(topics = "user-notify-topic", groupId = "my-group-order", containerFactory = "userCreatedByAuthKafkaListenerFactory")
    public void consumedOrder(UserCreatedEvent userCreatedEvent) {
        userService.createUserFromAuthService(userCreatedEvent);
        System.out.println("Received User Data : " + userCreatedEvent.toString());
    }
}
