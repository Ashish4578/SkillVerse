package com.skillverse.courseservice.repository;

import com.skillverse.courseservice.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

    List<Lesson> findByModuleModuleIdOrderBySequenceNumberAsc( Long moduleId);

    long countByModuleCourseCourseId( Long courseId);

    long countByModuleModuleId( Long moduleId);
}