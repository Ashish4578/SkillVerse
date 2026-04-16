package com.skillverse.ratingservice.repo;

import com.skillverse.ratingservice.entity.Rating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

    // Get ratings by course
    Page<Rating> findByCourseId(Long courseId, Pageable pageable);

    // Get ratings by user
    Page<Rating> findByUserId(Long userId, Pageable pageable);

    // Prevent duplicate rating
    Optional<Rating> findByUserIdAndCourseId(Long userId, Long courseId);

    boolean existsByUserIdAndCourseId(Long userId, Long courseId);

    // Aggregate (average rating)
    @Query("SELECT AVG(r.rating) FROM Rating r WHERE r.courseId = :courseId")
    Double findAverageRatingByCourseId(@Param("courseId") Long courseId);

    @Query("SELECT COUNT(r) FROM Rating r WHERE r.courseId = :courseId")
    Long countByCourseId(@Param("courseId") Long courseId);
}