package com.skillverse.userservice.config;

import com.skillverse.userservice.entity.UserCreatedEvent;
import com.skillverse.userservice.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class MyKafkaConsumer {

    private final UserService userService;

    @KafkaListener(topics = "user-notify-topic", groupId = "my-group-order", containerFactory = "userCreatedByAuthKafkaListenerFactory")
    public void consumedOrder(UserCreatedEvent userCreatedEvent) {
        userService.createUserFromAuthService(userCreatedEvent);
        log.info("Received User Data : " + userCreatedEvent.toString());
    }
}
