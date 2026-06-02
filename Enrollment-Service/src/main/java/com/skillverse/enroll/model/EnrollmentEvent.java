package com.skillverse.enroll.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnrollmentEvent {

    private UserInfo user;
    private String message;
    private CourseInfo course;
    private String status;
}