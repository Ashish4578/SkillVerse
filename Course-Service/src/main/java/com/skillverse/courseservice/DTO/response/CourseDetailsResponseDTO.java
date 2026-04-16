package com.skillverse.courseservice.DTO.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CourseDetailsResponseDTO {

    private Long courseId;

    private String courseName;

    private String courseDescription;

    private String courseInstructor;

    private double coursePrice;

    private String courseDuration;

    private String courseTime;
}