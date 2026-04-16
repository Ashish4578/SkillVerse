package com.skillverse.courseservice.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ValidationErrorResponse {

    private int status;
    private String errorCode;
    private Map<String, String> errors;
    private LocalDateTime timestamp;
}