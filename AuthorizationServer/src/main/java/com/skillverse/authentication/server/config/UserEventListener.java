package com.skillverse.authentication.server.config;


import com.skillverse.authentication.server.entity.User;
import com.skillverse.authentication.server.entity.UserCreatedDomainEvent;
import com.skillverse.authentication.server.entity.UserCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserEventListener {

    private final UserCreatedProducer producer;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handle(UserCreatedDomainEvent event) {

        User user = event.getUser();

        UserCreatedEvent kafkaEvent = UserCreatedEvent.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .enabled(user.isEnabled())
                .createdAt(convert(user.getCreatedAt()))
                .updatedAt(convert(user.getUpdatedAt()))
                .build();

        producer.publish(kafkaEvent);
    }
    private LocalDateTime convert(Instant instant) {
        return instant == null ? null :
                LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }
}