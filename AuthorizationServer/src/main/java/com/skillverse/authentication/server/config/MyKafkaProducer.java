package com.skillverse.authentication.server.config;


import com.skillverse.authentication.server.entity.UserCreatedEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@AllArgsConstructor
@Component
public class MyKafkaProducer {

    private final KafkaTemplate<String, UserCreatedEvent> kafkaTemplate;

    public void sendUserDetailsToUserService(UserCreatedEvent userCreatedEvent) {
        System.out.println("Sending message to Consumer " + userCreatedEvent);
        kafkaTemplate.send("user-notify-topic", userCreatedEvent);
    }
}
