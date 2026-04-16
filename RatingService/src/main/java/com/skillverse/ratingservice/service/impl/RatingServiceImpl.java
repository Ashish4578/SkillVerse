package com.skillverse.ratingservice.service.impl;

import com.skillverse.ratingservice.client.CourseServiceClient;
import com.skillverse.ratingservice.client.RatingEventProducer;
import com.skillverse.ratingservice.dto.CourseRatingSummaryDTO;
import com.skillverse.ratingservice.dto.request.RatingRequestDTO;
import com.skillverse.ratingservice.dto.response.RatingResponseDTO;
import com.skillverse.ratingservice.entity.Rating;
import com.skillverse.ratingservice.entity.RatingCreatedDomainEvent;
import com.skillverse.ratingservice.entity.RatingCreatedEvent;
import com.skillverse.ratingservice.entity.UserRequestContext;
import com.skillverse.ratingservice.exception.DuplicateUserException;
import com.skillverse.ratingservice.exception.ResourceNotFoundException;
import com.skillverse.ratingservice.exception.UnauthorizedException;
import com.skillverse.ratingservice.mapper.RatingMapper;
import com.skillverse.ratingservice.repo.RatingRepository;
import com.skillverse.ratingservice.service.RatingService;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;
    private final RatingMapper ratingMapper;
    private final CourseServiceClient courseClient;
    private final ApplicationEventPublisher applicationEventPublisher;

    //  Rate course
    @Override
    public RatingResponseDTO rateCourse(UserRequestContext context,
                                        RatingRequestDTO request) {

        Long userId = context.getUserId();
        Long courseId = request.getCourseId();

        log.info("Rating course userId={} courseId={}", userId, courseId);

        // Validate rating range
        if (request.getRating() < 1 || request.getRating() > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5");
        }

        // Validate course exists
        try {
            courseClient.getCourseById(courseId, "internal");
        } catch (FeignException.NotFound ex) {
            throw new ResourceNotFoundException("Course not found");
        } catch (FeignException ex) {
            throw new RuntimeException("Course service unavailable");
        }

        // Prevent duplicate rating (soft check)
        if (ratingRepository.existsByUserIdAndCourseId(userId, courseId)) {
            throw new DuplicateUserException("You already rated this course");
        }

        Rating rating = Rating.builder()
                .userId(userId)
                .courseId(courseId)
                .rating(request.getRating())
                .review(request.getReview())
                .build();

        Rating saved = ratingRepository.save(rating);

        // Publish domain event
        applicationEventPublisher.publishEvent(
                new RatingCreatedDomainEvent(saved)
        );

        return ratingMapper.toDTO(saved);
    }

    // ✏ Update rating
    @Override
    public RatingResponseDTO updateRating(UserRequestContext context,
                                          Long ratingId,
                                          RatingRequestDTO request) {

        Rating rating = getRatingOrThrow(ratingId);

        validateOwnership(context, rating);

        ratingMapper.updateEntityFromDto(request, rating);

        return ratingMapper.toDTO(ratingRepository.save(rating));

    }

    //  Delete rating
    @Override
    public void deleteRating(UserRequestContext context, Long ratingId) {

        Rating rating = getRatingOrThrow(ratingId);

        validateOwnership(context, rating);

        ratingRepository.delete(rating);
    }

    //  Get ratings for a course
    @Override
    public Page<RatingResponseDTO> getRatingsByCourse(Long courseId,
                                                      Pageable pageable) {

        return ratingRepository.findByCourseId(courseId, pageable)
                .map(ratingMapper::toDTO);
    }

    //  Get my ratings
    @Override
    public Page<RatingResponseDTO> getMyRatings(UserRequestContext context,
                                                Pageable pageable) {

        return ratingRepository.findByUserId(context.getUserId(), pageable)
                .map(ratingMapper::toDTO);
    }

    //  Get average + count
    @Override
    public CourseRatingSummaryDTO getCourseRatingSummary(Long courseId) {

        Double avg = ratingRepository.findAverageRatingByCourseId(courseId);
        Long count = ratingRepository.countByCourseId(courseId);

        return CourseRatingSummaryDTO.builder()
                .courseId(courseId)
                .averageRating(avg != null ? avg : 0.0)
                .totalRatings(count != null ? count : 0)
                .build();
    }

    // ================= HELPER METHODS =================

    private Rating getRatingOrThrow(Long id) {
        return ratingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rating not found"));
    }

    private void validateOwnership(UserRequestContext context, Rating rating) {
        if (!rating.getUserId().equals(context.getUserId())) {
            throw new UnauthorizedException("You can only modify your own rating");
        }
    }
}