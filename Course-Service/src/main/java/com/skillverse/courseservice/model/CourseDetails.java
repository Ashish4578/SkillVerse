package com.skillverse.courseservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@NoArgsConstructor
@Data
public class CourseDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;

    @NotBlank(message = "Course name cannot be blank")
    @Size(min = 3, max = 20, message = "Course name must be between 3 and 100 characters")
    private String courseName;

    @NotBlank(message = "Course description cannot be blank")
    @Size(min = 3, max = 100, message = "Course description must be between 3 and 100 characters")
    private String courseDescription;

    @NotBlank(message = "Course instructor cannot be blank")
    @Size(min = 3, max = 20, message = "Course instructor must be mentioned")
    private String courseInstructor;


    private String courseDuration;

    private double coursePrice;

    @NotBlank(message = "Course time cannot be blank")
    private String courseTime;

}
