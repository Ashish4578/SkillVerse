package com.skillverse.ratingservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRequestContext {
    private Long userId;
    private String role;
}