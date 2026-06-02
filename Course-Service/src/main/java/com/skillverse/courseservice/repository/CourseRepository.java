package com.skillverse.courseservice.repository;

import com.skillverse.courseservice.model.CourseDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository
        extends JpaRepository<CourseDetails, Long> {

    List<CourseDetails>
    findByCourseNameContainingIgnoreCase(
            String keyword);

    List<CourseDetails>
    findByCourseNameContainingIgnoreCaseOrCourseDescriptionContainingIgnoreCase(
            String nameKeyword,
            String descriptionKeyword);

    List<CourseDetails>
    findByCreatedBy(
            Long userId);

    Page<CourseDetails>
    findAll(
            Pageable pageable);

    Page<CourseDetails>
    findByCourseNameContainingIgnoreCaseOrCourseDescriptionContainingIgnoreCase(
            String name,
            String description,
            Pageable pageable);
}