package com.skillverse.courseservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ModuleProgressResponseDTO {

    private Long moduleId;

    private long completedLessons;

    private long totalLessons;

    private double percentage;
}