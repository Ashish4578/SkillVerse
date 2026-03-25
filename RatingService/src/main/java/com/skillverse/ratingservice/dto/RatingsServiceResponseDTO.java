package com.skillverse.ratingservice.dto;

import com.skillverse.ratingservice.entity.Rating;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RatingsServiceResponseDTO {
    private Long creatorId;
    private Long courseId;
    private Rating rating;
}
