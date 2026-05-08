package com.skillverse.courseservice.config;

import com.skillverse.courseservice.model.RatingCreatedEvent;
import com.skillverse.courseservice.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MyKafkaConsumer {

    private final CourseService courseService;

    @KafkaListener(topics = "course-notify-topic", groupId = "my-group-order", containerFactory = "ratingToCourseAuthKafkaListenerFactory")
    public void consumedOrder(RatingCreatedEvent ratingCreatedEvent) {
        courseService.updateCourseRating(ratingCreatedEvent.getCourseId(), ratingCreatedEvent.getRating());
        System.out.println("Received User Data : " + ratingCreatedEvent.toString());
    }
}
