package com.skillverse.courseservice.client;

import com.skillverse.courseservice.model.RatingCreatedEvent;
import com.skillverse.courseservice.service.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class RatingEventConsumer {

    private final CourseService courseService;

    @KafkaListener(
            topics = "rating-created",
            groupId = "course-group"
    )
    public void consume(RatingCreatedEvent event) {

        log.info("Received rating event: {}", event);

        courseService.updateCourseRating(
                event.getCourseId(),
                event.getRating()
        );
    }
}