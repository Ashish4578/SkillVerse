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
            if (userRepository.existsById(event.getId())) {
                log.warn("User already exists id={}", event.getId());
                return;
            }

            UserProfile user = UserProfile.builder()
                    .id(event.getId())
                    .username(event.getUsername())
                    .email(event.getEmail())
                    .contactNumber(event.getContactNumber())
                    .enabled(event.isEnabled())
                    .createdAt(event.getCreatedAt())
                    .updatedAt(event.getUpdatedAt())
                    .build();

            userRepository.save(user);

            log.info(" User saved successfully id={}", user.getId());

        } catch (Exception ex) {
            log.error(" Failed to process user-created event id={}", event.getId(), ex);

            //  IMPORTANT: throw exception so Kafka retries
            throw ex;
        }
    }
}