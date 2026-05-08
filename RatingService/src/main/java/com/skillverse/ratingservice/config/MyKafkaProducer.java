package com.skillverse.ratingservice.config;


import com.skillverse.ratingservice.entity.RatingCreatedEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@AllArgsConstructor
@Component
public class MyKafkaProducer {

    private final KafkaTemplate<String, RatingCreatedEvent> kafkaTemplate;

    public void sendUserDetailsToUserService(RatingCreatedEvent userCreatedEvent) {
        System.out.println("Sending message to Consumer " + userCreatedEvent);
        kafkaTemplate.send("course-notify-topic", userCreatedEvent);
    }
}
