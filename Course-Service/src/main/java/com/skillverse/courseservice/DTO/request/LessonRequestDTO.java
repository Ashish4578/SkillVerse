package com.skillverse.courseservice.DTO.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LessonRequestDTO {

    @NotBlank
    private String lessonTitle;

    @Min(1)
    private Integer sequenceNumber;
}