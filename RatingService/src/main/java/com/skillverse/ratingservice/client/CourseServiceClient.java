package com.skillverse.ratingservice.client;

import com.skillverse.ratingservice.dto.response.CourseDetailsResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "COURSE-SERVICE")
public interface CourseServiceClient {

    @GetMapping("/courses/internal/{courseId}")
    CourseDetailsResponseDTO getCourseById(
            @PathVariable("courseId") Long courseId,
            @RequestHeader("X-Internal-Call") String internal
    );
}