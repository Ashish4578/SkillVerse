package com.skillverse.ratingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CourseRatingSummaryDTO {

    private Long courseId;
    private double averageRating;
    private long totalRatings;
}