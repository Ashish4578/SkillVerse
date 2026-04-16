package com.skillverse.ratingservice.controller;

import com.skillverse.ratingservice.dto.CourseRatingSummaryDTO;
import com.skillverse.ratingservice.dto.request.RatingRequestDTO;
import com.skillverse.ratingservice.dto.response.RatingResponseDTO;
import com.skillverse.ratingservice.entity.HeaderConstants;
import com.skillverse.ratingservice.entity.UserRequestContext;
import com.skillverse.ratingservice.exception.UnauthorizedException;
import com.skillverse.ratingservice.service.RatingService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ratings")
@RequiredArgsConstructor
@Slf4j
@Validated
public class RatingController {

    private final RatingService ratingService;

    private static final String INTERNAL_GATEWAY = "gateway";

    //  Build context (same pattern everywhere)
    private UserRequestContext buildContext(Long userId, String role, String internal) {
        if (!INTERNAL_GATEWAY.equals(internal)) {
            throw new UnauthorizedException("Unauthorized access");
        }
        return new UserRequestContext(userId, role);
    }

    // Rate a course
    @PostMapping
    public ResponseEntity<RatingResponseDTO> rateCourse(
            @RequestHeader(HeaderConstants.USER_ID) @NotNull Long userId,
            @RequestHeader(HeaderConstants.USER_ROLE) @NotBlank String role,
            @RequestHeader(HeaderConstants.INTERNAL_CALL) @NotBlank String internal,
            @RequestBody @Valid RatingRequestDTO request) {

        log.info("RatingController :: rateCourse userId={} courseId={}",
                userId, request.getCourseId());

        UserRequestContext context = buildContext(userId, role, internal);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ratingService.rateCourse(context, request));
    }

    // ️ Update rating
    @PutMapping("/{ratingId}")
    public ResponseEntity<RatingResponseDTO> updateRating(
            @PathVariable Long ratingId,
            @RequestHeader(HeaderConstants.USER_ID) Long userId,
            @RequestHeader(HeaderConstants.USER_ROLE) String role,
            @RequestHeader(HeaderConstants.INTERNAL_CALL) String internal,
            @RequestBody @Valid RatingRequestDTO request) {

        log.info("Updating ratingId={} by userId={}", ratingId, userId);

        UserRequestContext context = buildContext(userId, role, internal);

        return ResponseEntity.ok(
                ratingService.updateRating(context, ratingId, request)
        );
    }

    //  Delete rating
    @DeleteMapping("/{ratingId}")
    public ResponseEntity<Void> deleteRating(
            @PathVariable Long ratingId,
            @RequestHeader(HeaderConstants.USER_ID) Long userId,
            @RequestHeader(HeaderConstants.USER_ROLE) String role,
            @RequestHeader(HeaderConstants.INTERNAL_CALL) String internal) {

        log.info("Deleting ratingId={} by userId={}", ratingId, userId);

        UserRequestContext context = buildContext(userId, role, internal);

        ratingService.deleteRating(context, ratingId);
        return ResponseEntity.noContent().build();
    }

    //  Get ratings for a course (PUBLIC)
    @GetMapping("/course/{courseId}")
    public ResponseEntity<Page<RatingResponseDTO>> getRatingsByCourse(
            @PathVariable Long courseId,
            @PageableDefault(size = 5, sort = "createdAt", direction = Sort.Direction.DESC)
            Pageable pageable) {

        return ResponseEntity.ok(
                ratingService.getRatingsByCourse(courseId, pageable)
        );
    }

    //  Get my ratings
    @GetMapping("/me")
    public ResponseEntity<Page<RatingResponseDTO>> getMyRatings(
            @RequestHeader(HeaderConstants.USER_ID) Long userId,
            @RequestHeader(HeaderConstants.USER_ROLE) String role,
            @RequestHeader(HeaderConstants.INTERNAL_CALL) String internal,
            @PageableDefault(size = 5, sort = "createdAt", direction = Sort.Direction.DESC)
            Pageable pageable) {

        UserRequestContext context = buildContext(userId, role, internal);

        return ResponseEntity.ok(
                ratingService.getMyRatings(context, pageable)
        );
    }

    //  Get average rating
    @GetMapping("/course/{courseId}/summary")
    public ResponseEntity<CourseRatingSummaryDTO> getCourseSummary(
            @PathVariable Long courseId) {

        return ResponseEntity.ok(
                ratingService.getCourseRatingSummary(courseId)
        );
    }
}