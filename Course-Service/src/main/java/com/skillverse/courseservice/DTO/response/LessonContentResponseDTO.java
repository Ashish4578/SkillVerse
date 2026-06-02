package com.skillverse.courseservice.DTO.response;

import com.skillverse.courseservice.model.ContentType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LessonContentResponseDTO {

    private Long contentId;

    private ContentType contentType;

    private String contentUrl;

    private String textContent;

    private Long lessonId;
}