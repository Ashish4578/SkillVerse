package com.skillverse.courseservice.DTO;

import lombok.Data;

@Data
public class CourseDetailsRequestDTO {

    private String courseName;
    private String courseDescription;
    private String courseInstructor;
    private double coursePrice;
    private String courseDuration;
    private String courseTime;

}
