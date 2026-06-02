package com.skillverse.courseservice.repository;

import com.skillverse.courseservice.model.CourseProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseProgressRepository extends JpaRepository<CourseProgress, Long> {

    boolean existsByUserIdAndLessonId( Long userId, Long lessonId);

    long countByUserIdAndCourseId( Long userId, Long courseId);

    @Query("""
            SELECT COUNT(cp)
            FROM CourseProgress cp
            JOIN Lesson l
                ON cp.lessonId = l.lessonId
            WHERE cp.userId = :userId
            AND l.module.moduleId = :moduleId
            """)
    long countCompletedLessonsByModule( @Param("userId") Long userId, @Param("moduleId") Long moduleId);
}