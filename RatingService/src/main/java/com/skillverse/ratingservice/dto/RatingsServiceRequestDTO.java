package com.skillverse.ratingservice.dto;

import com.skillverse.ratingservice.entity.Rating;
import lombok.Data;

@Data
public class RatingsServiceRequestDTO {
    private Long creatorId;
    private Long courseId;
    private Rating rating;

}
