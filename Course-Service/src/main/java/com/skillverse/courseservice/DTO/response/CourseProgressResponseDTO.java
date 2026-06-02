package com.skillverse.courseservice.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseProgressResponseDTO {

    private Long courseId;

    private long completedLessons;

    private long totalLessons;

    private double percentage;
}