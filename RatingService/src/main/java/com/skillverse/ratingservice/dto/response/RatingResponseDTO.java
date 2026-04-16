package com.skillverse.ratingservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RatingResponseDTO {

    private Long id;
    private Long userId;
    private Long courseId;

    private int rating;
    private String review;

    private LocalDateTime createdAt;
}