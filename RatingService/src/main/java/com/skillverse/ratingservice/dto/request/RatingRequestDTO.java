package com.skillverse.ratingservice.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RatingRequestDTO {

    @NotNull
    private Long courseId;

    @Min(1)
    @Max(5)
    private int rating;

    private String review;
}
