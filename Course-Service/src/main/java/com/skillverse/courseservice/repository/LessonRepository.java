package com.skillverse.courseservice.repository;

import com.skillverse.courseservice.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {

    List<Lesson> findByModuleModuleIdOrderBySequenceNumberAsc( Long moduleId);

    long countByModuleCourseCourseId( Long courseId);

    long countByModuleModuleId( Long moduleId);
}