package com.skillverse.courseservice.controller;

import com.skillverse.courseservice.DTO.request.CourseModuleRequestDTO;
import com.skillverse.courseservice.DTO.response.CourseModuleResponseDTO;
import com.skillverse.courseservice.execption.UnauthorizedException;
import com.skillverse.courseservice.model.HeaderConstants;
import com.skillverse.courseservice.model.UserRequestContext;
import com.skillverse.courseservice.service.CourseModuleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@Slf4j
@Validated
@RequestMapping("/courses")
public class CourseModuleController {

    private final CourseModuleService courseModuleService;

    private static final Set<String> ALLOWED_INTERNAL = Set.of("gateway");

    private UserRequestContext buildContext(
            Long userId,
            String role,
            String internal) {

        if (!ALLOWED_INTERNAL.contains(internal)) {
            throw new UnauthorizedException("Unauthorized access");
        }

        return new UserRequestContext(userId, role);
    }

    @PostMapping("/{courseId}/modules")
    public ResponseEntity<CourseModuleResponseDTO> createModule(
            @PathVariable Long courseId,
            @RequestHeader(HeaderConstants.USER_ID) Long userId,
            @RequestHeader(HeaderConstants.USER_ROLE) String role,
            @RequestHeader(HeaderConstants.INTERNAL_CALL) String internal,
            @Valid @RequestBody CourseModuleRequestDTO request) {

        log.info(
                "CourseModuleController :: createModule courseId={} userId={}",
                courseId,
                userId);

        UserRequestContext context =
                buildContext(userId, role, internal);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(courseModuleService.createModule(
                        context,
                        courseId,
                        request));
    }

    @GetMapping("/{courseId}/modules")
    public ResponseEntity<List<CourseModuleResponseDTO>> getModulesByCourse(
            @PathVariable Long courseId) {

        log.info(
                "CourseModuleController :: getModulesByCourse courseId={}",
                courseId);

        return ResponseEntity.ok(
                courseModuleService.getModulesByCourse(courseId));
    }

    @PutMapping("/modules/{moduleId}")
    public ResponseEntity<CourseModuleResponseDTO> updateModule(
            @PathVariable Long moduleId,
            @RequestHeader(HeaderConstants.USER_ID) Long userId,
            @RequestHeader(HeaderConstants.USER_ROLE) String role,
            @RequestHeader(HeaderConstants.INTERNAL_CALL) String internal,
            @Valid @RequestBody CourseModuleRequestDTO request) {

        log.info(
                "CourseModuleController :: updateModule moduleId={} userId={}",
                moduleId,
                userId);

        UserRequestContext context =
                buildContext(userId, role, internal);

        return ResponseEntity.ok(
                courseModuleService.updateModule(
                        context,
                        moduleId,
                        request));
    }

    @DeleteMapping("/modules/{moduleId}")
    public ResponseEntity<Void> deleteModule(
            @PathVariable Long moduleId,
            @RequestHeader(HeaderConstants.USER_ID) Long userId,
            @RequestHeader(HeaderConstants.USER_ROLE) String role,
            @RequestHeader(HeaderConstants.INTERNAL_CALL) String internal) {

        log.info(
                "CourseModuleController :: deleteModule moduleId={} userId={}",
                moduleId,
                userId);

        UserRequestContext context =
                buildContext(userId, role, internal);

        courseModuleService.deleteModule(
                context,
                moduleId);

        return ResponseEntity.noContent().build();
    }
}