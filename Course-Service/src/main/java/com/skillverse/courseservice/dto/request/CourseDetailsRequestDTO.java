package com.skillverse.courseservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CourseDetailsRequestDTO {

    @NotBlank
    @Size(min = 3, max = 100)
    private String courseName;

    @NotBlank
    @Size(min = 3, max = 500)
    private String courseDescription;

    @NotBlank
    private String courseInstructor;

    @PositiveOrZero
    private double coursePrice;

    private String courseDuration;

    private String thumbnailUrl;

    @NotBlank
    private String courseTime;
}