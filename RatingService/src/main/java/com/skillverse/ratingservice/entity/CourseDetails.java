package com.skillverse.ratingservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "course_details")
@Entity
public class CourseDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotBlank(message = "Course ID cannot be blank")
    private Long courseId;

    @NotBlank(message = "Course name cannot be blank")
    @Size(min = 3, max = 20, message = "Course name must be between 3 and 100 characters")
    private String courseName;

    @NotBlank(message = "Course instructor cannot be blank")
    @Size(min = 3, max = 20, message = "Course instructor must be mentioned")
    private String courseInstructor;

}
