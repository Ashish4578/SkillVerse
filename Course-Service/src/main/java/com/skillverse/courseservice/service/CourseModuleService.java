package com.skillverse.courseservice.service;

import com.skillverse.courseservice.DTO.request.CourseModuleRequestDTO;
import com.skillverse.courseservice.DTO.response.CourseModuleResponseDTO;
import com.skillverse.courseservice.model.UserRequestContext;

import java.util.List;

public interface CourseModuleService {
    CourseModuleResponseDTO createModule(
            UserRequestContext context,
            Long courseId,
            CourseModuleRequestDTO request);

    List<CourseModuleResponseDTO> getModulesByCourse(
            Long courseId);

    CourseModuleResponseDTO updateModule(
            UserRequestContext context,
            Long moduleId,
            CourseModuleRequestDTO request);

    void deleteModule(
            UserRequestContext context,
            Long moduleId);
}
