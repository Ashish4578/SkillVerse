package com.skillverse.courseservice.service;

import com.skillverse.courseservice.DTO.request.LessonContentRequestDTO;
import com.skillverse.courseservice.DTO.response.LessonContentResponseDTO;
import com.skillverse.courseservice.model.UserRequestContext;

import java.util.List;

public interface LessonContentService {

    LessonContentResponseDTO createContent(
            UserRequestContext context,
            Long lessonId,
            LessonContentRequestDTO request);

    List<LessonContentResponseDTO> getContentsByLesson(
            Long lessonId);

    LessonContentResponseDTO updateContent(
            UserRequestContext context,
            Long contentId,
            LessonContentRequestDTO request);

    void deleteContent(
            UserRequestContext context,
            Long contentId);
}