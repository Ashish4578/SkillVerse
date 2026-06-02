package com.skillverse.enroll.model;

import lombok.Data;

@Data
public class CourseInfo {
    private Long courseId;

    private String courseName;

    private String courseDescription;

    private String courseInstructor;

    private double coursePrice;

    private String courseDuration;

    private String courseTime;
}