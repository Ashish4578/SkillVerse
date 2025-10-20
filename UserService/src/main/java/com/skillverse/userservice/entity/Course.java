package com.skillverse.userservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Data
public class Course {
    private String courseId;
    private String courseName;
    private String courseDescription;
    private String courseImg;
    private float coursePrice;
    private String courseCategory;
    private String courseCreatorName;
    private String courseCreatorPhone;
    private String courseCreatorEmail;
}
