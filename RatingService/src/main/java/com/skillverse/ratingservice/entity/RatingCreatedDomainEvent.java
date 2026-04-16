package com.skillverse.ratingservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RatingCreatedDomainEvent {
    private Rating rating;
}