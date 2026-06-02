package com.skillverse.courseservice.service;

import com.skillverse.courseservice.DTO.response.CourseProgressResponseDTO;
import com.skillverse.courseservice.DTO.response.ModuleProgressResponseDTO;
import com.skillverse.courseservice.model.UserRequestContext;

public interface CourseProgressService {

    void completeLesson(UserRequestContext context, Long lessonId);

    CourseProgressResponseDTO getCourseProgress(UserRequestContext context, Long courseId);

    ModuleProgressResponseDTO getModuleProgress( UserRequestContext context, Long courseId,Long moduleId);
}