package com.skillverse.ratingservice.client;

import com.skillverse.ratingservice.entity.Rating;
import com.skillverse.ratingservice.entity.RatingCreatedDomainEvent;
import com.skillverse.ratingservice.entity.RatingCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
@Slf4j
public class RatingEventListener {

    private final RatingEventProducer producer;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handle(RatingCreatedDomainEvent event) {

        Rating rating = event.getRating();

        RatingCreatedEvent kafkaEvent = RatingCreatedEvent.builder()
                .courseId(rating.getCourseId())
                .userId(rating.getUserId())
                .rating(rating.getRating())
                .build();

        producer.publish(kafkaEvent);
    }
}