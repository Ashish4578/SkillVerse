package com.skillverse.courseservice.service.impl;

import com.skillverse.courseservice.DTO.request.LessonContentRequestDTO;
import com.skillverse.courseservice.DTO.response.LessonContentResponseDTO;
import com.skillverse.courseservice.execption.ResourceNotFoundException;
import com.skillverse.courseservice.execption.UnauthorizedException;
import com.skillverse.courseservice.mapper.LessonContentMapper;
import com.skillverse.courseservice.model.*;
import com.skillverse.courseservice.repository.LessonContentRepository;
import com.skillverse.courseservice.repository.LessonRepository;
import com.skillverse.courseservice.service.LessonContentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class LessonContentServiceImpl
        implements LessonContentService {

    private final LessonRepository lessonRepository;
    private final LessonContentRepository lessonContentRepository;
    private final LessonContentMapper lessonContentMapper;

    private static final String ROLE_CREATOR =
            "ROLE_CREATOR";

    @Override
    public LessonContentResponseDTO createContent(
            UserRequestContext context,
            Long lessonId,
            LessonContentRequestDTO request) {

        validateCreator(context);

        Lesson lesson =
                getLessonOrThrow(lessonId);

        validateOwnership(
                context,
                lesson.getModule()
                        .getCourse());

        validateContent(request);

        LessonContent content =
                lessonContentMapper.toEntity(request);

        content.setLesson(lesson);

        return lessonContentMapper.toDTO(
                lessonContentRepository.save(content));
    }

    @Transactional(readOnly = true)
    @Override
    public List<LessonContentResponseDTO> getContentsByLesson(
            Long lessonId) {

        getLessonOrThrow(lessonId);

        return lessonContentRepository
                .findByLessonLessonId(lessonId)
                .stream()
                .map(lessonContentMapper::toDTO)
                .toList();
    }

    @Override
    public LessonContentResponseDTO updateContent(
            UserRequestContext context,
            Long contentId,
            LessonContentRequestDTO request) {

        validateCreator(context);

        LessonContent content =
                getContentOrThrow(contentId);

        validateOwnership(
                context,
                content.getLesson()
                        .getModule()
                        .getCourse());

        validateContent(request);

        lessonContentMapper.updateEntityFromDto(
                request,
                content);

        return lessonContentMapper.toDTO(
                lessonContentRepository.save(content));
    }

    @Override
    public void deleteContent(
            UserRequestContext context,
            Long contentId) {

        validateCreator(context);

        LessonContent content =
                getContentOrThrow(contentId);

        validateOwnership(
                context,
                content.getLesson()
                        .getModule()
                        .getCourse());

        lessonContentRepository.delete(content);
    }

    // =========================
    // Helper Methods
    // =========================

    private Lesson getLessonOrThrow(
            Long lessonId) {

        return lessonRepository.findById(lessonId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Lesson not found"));
    }

    private LessonContent getContentOrThrow(
            Long contentId) {

        return lessonContentRepository.findById(contentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Content not found"));
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

    private void validateContent(
            LessonContentRequestDTO request) {

        if (request.getContentType() ==
                ContentType.TEXT) {

            if (request.getTextContent() == null
                    || request.getTextContent()
                    .isBlank()) {

                throw new IllegalArgumentException(
                        "Text content cannot be empty");
            }
        }
    }
}