package com.skillverse.enroll.repo;

import com.skillverse.enroll.model.Enrollment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    // Prevent duplicate enrollment
    boolean existsByUserIdAndCourseId(Long userId, Long courseId);

    // Get all enrollments of a user
    Page<Enrollment> findByUserId(Long userId, Pageable pageable);

    // Get all enrollments for a course
    Page<Enrollment> findByCourseId(Long courseId, Pageable pageable);
}
