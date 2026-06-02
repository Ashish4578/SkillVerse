package com.skillverse.courseservice.DTO.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LessonResponseDTO {

    private Long lessonId;

    private String lessonTitle;

    private Integer sequenceNumber;

    private Long moduleId;
}