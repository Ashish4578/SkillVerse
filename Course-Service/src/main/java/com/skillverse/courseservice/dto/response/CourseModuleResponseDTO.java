package com.skillverse.courseservice.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CourseModuleResponseDTO {

    private Long moduleId;

    private String moduleName;

    private Integer sequenceNumber;

    private Long courseId;
}