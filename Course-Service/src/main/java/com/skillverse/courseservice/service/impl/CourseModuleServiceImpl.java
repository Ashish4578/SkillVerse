package com.skillverse.courseservice.service.impl;

import com.skillverse.courseservice.dto.request.CourseModuleRequestDTO;
import com.skillverse.courseservice.dto.response.CourseModuleResponseDTO;
import com.skillverse.courseservice.execption.ResourceNotFoundException;
import com.skillverse.courseservice.execption.UnauthorizedException;
import com.skillverse.courseservice.mapper.CourseModuleMapper;
import com.skillverse.courseservice.model.CourseDetails;
import com.skillverse.courseservice.model.CourseModule;
import com.skillverse.courseservice.model.UserRequestContext;
import com.skillverse.courseservice.repository.CourseModuleRepository;
import com.skillverse.courseservice.repository.CourseRepository;
import com.skillverse.courseservice.service.CourseModuleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class CourseModuleServiceImpl implements CourseModuleService {

    private final CourseRepository courseRepository;
    private final CourseModuleRepository courseModuleRepository;
    private final CourseModuleMapper courseModuleMapper;

    private static final String ROLE_CREATOR = "ROLE_CREATOR";

    @Override
    public CourseModuleResponseDTO createModule(
            UserRequestContext context,
            Long courseId,
            CourseModuleRequestDTO request) {

        validateCreator(context);

        CourseDetails course = getCourseOrThrow(courseId);

        validateOwnership(context, course);

        request.setModuleName(request.getModuleName().trim());

        CourseModule module = courseModuleMapper.toEntity(request);
        module.setCourse(course);

        return courseModuleMapper.toDTO(
                courseModuleRepository.save(module));
    }

    @Transactional(readOnly = true)
    @Override
    public List<CourseModuleResponseDTO> getModulesByCourse(Long courseId) {

        getCourseOrThrow(courseId);

        return courseModuleRepository
                .findByCourseCourseIdOrderBySequenceNumberAsc(courseId)
                .stream()
                .map(courseModuleMapper::toDTO)
                .toList();
    }

    @Override
    public CourseModuleResponseDTO updateModule(
            UserRequestContext context,
            Long moduleId,
            CourseModuleRequestDTO request) {

        validateCreator(context);

        CourseModule module = getModuleOrThrow(moduleId);

        validateOwnership(
                context,
                module.getCourse());

        request.setModuleName(request.getModuleName().trim());

        courseModuleMapper.updateEntityFromDto(
                request,
                module);

        return courseModuleMapper.toDTO(
                courseModuleRepository.save(module));
    }

    @Override
    public void deleteModule(
            UserRequestContext context,
            Long moduleId) {

        validateCreator(context);

        CourseModule module = getModuleOrThrow(moduleId);

        validateOwnership(
                context,
                module.getCourse());

        courseModuleRepository.delete(module);
    }

    // =========================
    // Helper Methods
    // =========================

    private CourseDetails getCourseOrThrow(Long courseId) {

        return courseRepository.findById(courseId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Course not found"));
    }

    private CourseModule getModuleOrThrow(Long moduleId) {

        return courseModuleRepository.findById(moduleId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Module not found"));
    }

    private void validateCreator(
            UserRequestContext context) {

        if (!ROLE_CREATOR.equals(
                context.getRole())) {

            throw new UnauthorizedException(
                    "Only creators can perform this action");
        }
    }

    private void validateOwnership(
            UserRequestContext context,
            CourseDetails course) {

        if (!course.getCreatedBy()
                .equals(context.getUserId())) {

            throw new UnauthorizedException(
                    "You are not owner of this course");
        }
    }
}