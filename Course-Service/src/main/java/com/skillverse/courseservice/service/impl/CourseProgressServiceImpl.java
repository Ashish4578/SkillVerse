package com.skillverse.courseservice.service.impl;

import com.skillverse.courseservice.DTO.response.CourseProgressResponseDTO;
import com.skillverse.courseservice.DTO.response.ModuleProgressResponseDTO;
import com.skillverse.courseservice.execption.ResourceNotFoundException;
import com.skillverse.courseservice.model.CourseProgress;
import com.skillverse.courseservice.model.Lesson;
import com.skillverse.courseservice.model.UserRequestContext;
import com.skillverse.courseservice.repository.CourseProgressRepository;
import com.skillverse.courseservice.repository.LessonRepository;
import com.skillverse.courseservice.service.CourseProgressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CourseProgressServiceImpl
        implements CourseProgressService {

    private final CourseProgressRepository
            courseProgressRepository;

    private final LessonRepository
            lessonRepository;

    @Override
    public void completeLesson(
            UserRequestContext context,
            Long lessonId) {

        Lesson lesson =
                lessonRepository.findById(lessonId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Lesson not found"));

        Long courseId =
                lesson.getModule()
                        .getCourse()
                        .getCourseId();

        boolean alreadyCompleted =
                courseProgressRepository
                        .existsByUserIdAndLessonId(
                                context.getUserId(),
                                lessonId);

        if (alreadyCompleted) {
            log.info(
                    "Lesson already completed userId={} lessonId={}",
                    context.getUserId(),
                    lessonId);

            return;
        }

        CourseProgress progress =
                CourseProgress.builder()
                        .userId(context.getUserId())
                        .courseId(courseId)
                        .lessonId(lessonId)
                        .completed(true)
                        .completedAt(LocalDateTime.now())
                        .build();

        courseProgressRepository.save(progress);

        log.info(
                "Lesson completed userId={} lessonId={}",
                context.getUserId(),
                lessonId);
    }

    @Transactional(readOnly = true)
    @Override
    public CourseProgressResponseDTO
    getCourseProgress(
            UserRequestContext context,
            Long courseId) {

        long totalLessons =
                lessonRepository
                        .countByModuleCourseCourseId(
                                courseId);

        long completedLessons =
                courseProgressRepository
                        .countByUserIdAndCourseId(
                                context.getUserId(),
                                courseId);

        double percentage =
                totalLessons == 0
                        ? 0
                        : ((double) completedLessons
                        / totalLessons) * 100;

        return CourseProgressResponseDTO
                .builder()
                .courseId(courseId)
                .completedLessons(completedLessons)
                .totalLessons(totalLessons)
                .percentage(
                        Math.round(
                                percentage * 100.0)
                                / 100.0)
                .build();
    }

    @Transactional(readOnly = true)
    @Override
    public ModuleProgressResponseDTO
    getModuleProgress(
            UserRequestContext context,
            Long courseId,
            Long moduleId) {

        long totalLessons =
                lessonRepository
                        .countByModuleModuleId(
                                moduleId);

        long completedLessons =
                courseProgressRepository
                        .countCompletedLessonsByModule(
                                context.getUserId(),
                                moduleId);

        double percentage =
                totalLessons == 0
                        ? 0
                        : ((double) completedLessons
                        / totalLessons) * 100;

        return ModuleProgressResponseDTO
                .builder()
                .moduleId(moduleId)
                .completedLessons(completedLessons)
                .totalLessons(totalLessons)
                .percentage(
                        Math.round(
                                percentage * 100.0)
                                / 100.0)
                .build();
    }
}