package com.skillverse.ratingservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RatingCreatedEvent {

    private Long courseId;
    private Long userId;
    private int rating;
}