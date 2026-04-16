package com.skillverse.ratingservice.service;

import com.skillverse.ratingservice.dto.CourseRatingSummaryDTO;
import com.skillverse.ratingservice.dto.request.RatingRequestDTO;
import com.skillverse.ratingservice.dto.response.RatingResponseDTO;
import com.skillverse.ratingservice.entity.UserRequestContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RatingService {

    RatingResponseDTO rateCourse(UserRequestContext context, RatingRequestDTO request);

    RatingResponseDTO updateRating(UserRequestContext context, Long ratingId, RatingRequestDTO request);

    void deleteRating(UserRequestContext context, Long ratingId);

    Page<RatingResponseDTO> getRatingsByCourse(Long courseId, Pageable pageable);

    Page<RatingResponseDTO> getMyRatings(UserRequestContext context, Pageable pageable);

    CourseRatingSummaryDTO getCourseRatingSummary(Long courseId);
}