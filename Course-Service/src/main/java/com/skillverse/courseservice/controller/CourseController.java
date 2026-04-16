package com.skillverse.courseservice.controller;

import com.skillverse.courseservice.DTO.request.CourseDetailsRequestDTO;
import com.skillverse.courseservice.DTO.response.CourseDetailsResponseDTO;
import com.skillverse.courseservice.execption.UnauthorizedException;
import com.skillverse.courseservice.model.HeaderConstants;
import com.skillverse.courseservice.model.UserRequestContext;
import com.skillverse.courseservice.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
@Slf4j
@Validated
public class CourseController {

    private final CourseService courseService;

    private static final Set<String> ALLOWED_INTERNAL = Set.of("gateway");
    private static final Set<String> ALLOWED_INTERNAL_ALL = Set.of("gateway", "internal");

    private UserRequestContext buildContext(Long userId, String role, String internal) {
        if (!ALLOWED_INTERNAL.contains(internal)) {
            throw new UnauthorizedException("Unauthorized access");
        }
        return new UserRequestContext(userId, role);
    }

    @GetMapping
    public ResponseEntity<Page<CourseDetailsResponseDTO>> getAllCourses(
            @PageableDefault(size = 5, sort = "courseName") Pageable pageable) {

        log.info("CourseController :: getAllCourses");
        return ResponseEntity.ok(courseService.getAllCourses(pageable));
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<CourseDetailsResponseDTO> getCourseById(@PathVariable Long courseId) {

        log.info("CourseController :: getCourseById courseId={}", courseId);
        return ResponseEntity.ok(courseService.getCourseById(courseId));
    }

    @PostMapping
    public ResponseEntity<CourseDetailsResponseDTO> createCourse(
            @RequestHeader(HeaderConstants.USER_ID) Long userId,
            @RequestHeader(HeaderConstants.USER_ROLE) String role,
            @RequestHeader(HeaderConstants.INTERNAL_CALL) String internal,
            @Valid @RequestBody CourseDetailsRequestDTO request) {

        log.info("CourseController :: createCourse userId={} role={}", userId, role);

        UserRequestContext context = buildContext(userId, role, internal);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(courseService.createCourse(context, request));
    }

    @PutMapping("/{courseId}")
    public ResponseEntity<CourseDetailsResponseDTO> updateCourse(
            @PathVariable Long courseId,
            @RequestHeader(HeaderConstants.USER_ID) Long userId,
            @RequestHeader(HeaderConstants.USER_ROLE) String role,
            @RequestHeader(HeaderConstants.INTERNAL_CALL) String internal,
            @Valid @RequestBody CourseDetailsRequestDTO request) {

        log.info("CourseController :: updateCourse courseId={} userId={}", courseId, userId);

        UserRequestContext context = buildContext(userId, role, internal);

        return ResponseEntity.ok(courseService.updateCourse(context, courseId, request));
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<Void> deleteCourse(
            @PathVariable Long courseId,
            @RequestHeader(HeaderConstants.USER_ID) Long userId,
            @RequestHeader(HeaderConstants.USER_ROLE) String role,
            @RequestHeader(HeaderConstants.INTERNAL_CALL) String internal) {

        log.info("CourseController :: deleteCourse courseId={} userId={}", courseId, userId);

        UserRequestContext context = buildContext(userId, role, internal);

        courseService.deleteCourse(context, courseId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<Page<CourseDetailsResponseDTO>> searchCourses(
            @RequestParam String keyword,
            @PageableDefault(size = 5) Pageable pageable) {

        log.info("CourseController :: searchCourses keyword={}", keyword);

        return ResponseEntity.ok(courseService.searchCourse(keyword, pageable));
    }

    //  INTERNAL API (for Feign)
    @GetMapping("/internal/{courseId}")
    public ResponseEntity<CourseDetailsResponseDTO> getCourseInternal(
            @PathVariable Long courseId,
            @RequestHeader(HeaderConstants.INTERNAL_CALL) String internal) {

        if (!ALLOWED_INTERNAL_ALL.contains(internal)) {
            throw new UnauthorizedException("Unauthorized");
        }

        return ResponseEntity.ok(courseService.getCourseById(courseId));
    }

}
