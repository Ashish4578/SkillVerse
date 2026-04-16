package com.skillverse.enroll.controller;

import com.skillverse.enroll.dto.request.EnrollmentRequestDTO;
import com.skillverse.enroll.dto.response.EnrollmentResponseDTO;
import com.skillverse.enroll.exception.UnauthorizedException;
import com.skillverse.enroll.model.HeaderConstants;
import com.skillverse.enroll.model.UserRequestContext;
import com.skillverse.enroll.service.EnrollmentService;
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

import java.util.Set;

@RestController
@RequestMapping("/enrollments")
@RequiredArgsConstructor
@Slf4j
@Validated
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    private static final Set<String> ALLOWED_INTERNAL = Set.of("gateway");
    private static final Set<String> ALLOWED_INTERNAL_ALL = Set.of("gateway", "internal");

    private UserRequestContext buildContext(Long userId, String role, String internal) {
        if (!ALLOWED_INTERNAL.contains(internal)) {
            throw new UnauthorizedException("Unauthorized access");
        }
        return new UserRequestContext(userId, role);
    }

    //  Enroll (STUDENT only)
    @PostMapping
    public ResponseEntity<EnrollmentResponseDTO> enroll(
            @RequestHeader(HeaderConstants.USER_ID) @NotNull Long userId,
            @RequestHeader(HeaderConstants.USER_ROLE) @NotBlank String role,
            @RequestHeader(HeaderConstants.INTERNAL_CALL) @NotBlank String internal,
            @Valid @RequestBody EnrollmentRequestDTO request) {

        log.info("EnrollmentController :: enroll userId={} courseId={}", userId, request.getCourseId());

        UserRequestContext context = buildContext(userId, role, internal);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(enrollmentService.enroll(context, request));
    }

    //  My enrollments
    @GetMapping("/me")
    public ResponseEntity<Page<EnrollmentResponseDTO>> getMyEnrollments(
            @RequestHeader(HeaderConstants.USER_ID) @NotNull Long userId,
            @RequestHeader(HeaderConstants.USER_ROLE) @NotBlank String role,
            @RequestHeader(HeaderConstants.INTERNAL_CALL) @NotBlank String internal,
            @PageableDefault(size = 5, sort = "enrolledAt", direction = Sort.Direction.DESC)
            Pageable pageable) {

        log.info("EnrollmentController :: getMyEnrollments userId={}", userId);

        UserRequestContext context = buildContext(userId, role, internal);

        return ResponseEntity.ok(enrollmentService.getMyEnrollments(context, pageable));
    }

    //  Enrollments by course
    @GetMapping("/course/{courseId}")
    public ResponseEntity<Page<EnrollmentResponseDTO>> getEnrollmentsByCourse(
            @PathVariable Long courseId,
            @PageableDefault(size = 5) Pageable pageable) {

        log.info("EnrollmentController :: getEnrollmentsByCourse courseId={}", courseId);

        return ResponseEntity.ok(enrollmentService.getEnrollmentsByCourse(courseId, pageable));
    }

    //  INTERNAL API (for Feign / future services)
    @GetMapping("/internal/user/{userId}")
    public ResponseEntity<Page<EnrollmentResponseDTO>> getEnrollmentsByUserInternal(
            @PathVariable Long userId,
            @RequestHeader(HeaderConstants.INTERNAL_CALL) String internal,
            @PageableDefault(size = 5) Pageable pageable) {

        if (!ALLOWED_INTERNAL_ALL.contains(internal)) {
            throw new UnauthorizedException("Unauthorized");
        }

        log.info("EnrollmentController :: internal getEnrollmentsByUser userId={}", userId);

        return ResponseEntity.ok(
                enrollmentService.getMyEnrollments(
                        new UserRequestContext(userId, "INTERNAL"), pageable
                )
        );
    }
}