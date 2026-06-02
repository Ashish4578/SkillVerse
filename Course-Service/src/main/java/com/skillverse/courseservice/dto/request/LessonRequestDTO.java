package com.skillverse.courseservice.dto.request;

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