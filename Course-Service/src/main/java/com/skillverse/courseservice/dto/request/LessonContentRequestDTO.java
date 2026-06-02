package com.skillverse.courseservice.dto.request;

import com.skillverse.courseservice.model.ContentType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LessonContentRequestDTO {

    @NotNull
    private ContentType contentType;

    private String contentUrl;

    private String textContent;
}