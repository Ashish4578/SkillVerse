package com.skillverse.courseservice.repository;

import com.skillverse.courseservice.model.CourseDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<CourseDetails, Long> {

    // Search by course name (case-insensitive)
    List<CourseDetails> findByCourseNameContainingIgnoreCase(String keyword);

    // Advanced search (name OR description)
    List<CourseDetails> findByCourseNameContainingIgnoreCaseOrCourseDescriptionContainingIgnoreCase(
            String nameKeyword,
            String descriptionKeyword
    );

    // Find courses created by a user (VERY IMPORTANT)
    List<CourseDetails> findByCreatedBy(Long userId);

    // Pagination (future-ready)
    Page<CourseDetails> findAll(Pageable pageable);

    Page<CourseDetails> findByCourseNameContainingIgnoreCaseOrCourseDescriptionContainingIgnoreCase(
            String name,
            String description,
            Pageable pageable
    );
}