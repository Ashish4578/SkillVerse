package com.skillverse.courseservice.service;

import com.skillverse.courseservice.DTO.request.LessonRequestDTO;
import com.skillverse.courseservice.DTO.response.LessonResponseDTO;
import com.skillverse.courseservice.model.UserRequestContext;

import java.util.List;

public interface LessonService {

    LessonResponseDTO createLesson(
            UserRequestContext context,
            Long moduleId,
            LessonRequestDTO request);

    List<LessonResponseDTO> getLessonsByModule(
            Long moduleId);

    LessonResponseDTO updateLesson(
            UserRequestContext context,
            Long lessonId,
            LessonRequestDTO request);

    void deleteLesson(
            UserRequestContext context,
            Long lessonId);
}