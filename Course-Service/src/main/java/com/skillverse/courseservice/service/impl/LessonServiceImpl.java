package com.skillverse.courseservice.service.impl;


import com.skillverse.courseservice.DTO.request.LessonRequestDTO;
import com.skillverse.courseservice.DTO.response.LessonResponseDTO;
import com.skillverse.courseservice.execption.ResourceNotFoundException;
import com.skillverse.courseservice.execption.UnauthorizedException;
import com.skillverse.courseservice.mapper.LessonMapper;
import com.skillverse.courseservice.model.CourseDetails;
import com.skillverse.courseservice.model.CourseModule;
import com.skillverse.courseservice.model.Lesson;
import com.skillverse.courseservice.model.UserRequestContext;
import com.skillverse.courseservice.repository.CourseModuleRepository;
import com.skillverse.courseservice.repository.LessonRepository;
import com.skillverse.courseservice.service.LessonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;
    private final CourseModuleRepository courseModuleRepository;
    private final LessonMapper lessonMapper;

    private static final String ROLE_CREATOR = "ROLE_CREATOR";

    @Override
    public LessonResponseDTO createLesson(
            UserRequestContext context,
            Long moduleId,
            LessonRequestDTO request) {

        validateCreator(context);

        CourseModule module = getModuleOrThrow(moduleId);

        validateOwnership(
                context,
                module.getCourse());

        request.setLessonTitle(
                request.getLessonTitle().trim());

        Lesson lesson =
                lessonMapper.toEntity(request);

        lesson.setModule(module);

        return lessonMapper.toDTO(
                lessonRepository.save(lesson));
    }

    @Transactional(readOnly = true)
    @Override
    public List<LessonResponseDTO> getLessonsByModule(
            Long moduleId) {

        getModuleOrThrow(moduleId);

        return lessonRepository
                .findByModuleModuleIdOrderBySequenceNumberAsc(moduleId)
                .stream()
                .map(lessonMapper::toDTO)
                .toList();
    }

    @Override
    public LessonResponseDTO updateLesson(
            UserRequestContext context,
            Long lessonId,
            LessonRequestDTO request) {

        validateCreator(context);

        Lesson lesson =
                getLessonOrThrow(lessonId);

        validateOwnership(
                context,
                lesson.getModule().getCourse());

        request.setLessonTitle(
                request.getLessonTitle().trim());

        lessonMapper.updateEntityFromDto(
                request,
                lesson);

        return lessonMapper.toDTO(
                lessonRepository.save(lesson));
    }

    @Override
    public void deleteLesson(
            UserRequestContext context,
            Long lessonId) {

        validateCreator(context);

        Lesson lesson =
                getLessonOrThrow(lessonId);

        validateOwnership(
                context,
                lesson.getModule().getCourse());

        lessonRepository.delete(lesson);
    }

    // ==========================
    // Helpers
    // ==========================

    private CourseModule getModuleOrThrow(
            Long moduleId) {

        return courseModuleRepository.findById(moduleId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Module not found"));
    }

    private Lesson getLessonOrThrow(
            Long lessonId) {

        return lessonRepository.findById(lessonId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Lesson not found"));
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