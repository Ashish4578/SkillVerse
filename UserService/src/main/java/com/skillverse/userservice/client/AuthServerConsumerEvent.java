package com.skillverse.userservice.client;

import com.skillverse.userservice.entity.UserCreatedEvent;
import com.skillverse.userservice.entity.UserProfile;
import com.skillverse.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthServerConsumerEvent {

    private final UserRepository userRepository;

    @KafkaListener(
            topics = "user-created",
            groupId = "user-group",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void consume(UserCreatedEvent event) {

        log.info("📩 Received user-created event: {}", event);

        try {

            //  Idempotency check
            if (userRepository.existsByUsername(event.getUsername())) {
                log.warn("User already exists id={}", event.getUsername());
                return;
            }

            UserProfile user = UserProfile.builder()
                    .username(event.getUsername())
                    .email(event.getEmail())
                    .contactNumber(event.getContactNumber())
                    .enabled(event.isEnabled())
                    .createdAt(event.getCreatedAt())
                    .updatedAt(event.getUpdatedAt())
                    .build();

            userRepository.save(user);

            log.info(" User saved successfully name={}", user.getUsername());

        } catch (Exception ex) {
            log.error(" Failed to process user-created event username={}", event.getUsername(), ex);

            //  IMPORTANT: throw exception so Kafka retries
            throw ex;
        }
    }
}