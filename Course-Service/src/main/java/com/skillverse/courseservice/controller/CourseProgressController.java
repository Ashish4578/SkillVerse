package com.skillverse.courseservice.controller;

import com.skillverse.courseservice.execption.UnauthorizedException;
import com.skillverse.courseservice.model.HeaderConstants;
import com.skillverse.courseservice.model.UserRequestContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/courses/progress")
@RequiredArgsConstructor
@Slf4j
@Validated
public class CourseProgressController {

    private final CourseProgressService courseProgressService;

    private static final Set<String> ALLOWED_INTERNAL =
            Set.of("gateway");

    private UserRequestContext buildContext(
            Long userId,
            String role,
            String internal) {

        if (!ALLOWED_INTERNAL.contains(internal)) {
            throw new UnauthorizedException(
                    "Unauthorized access");
        }

        return new UserRequestContext(
                userId,
                role);
    }

    @PostMapping("/lessons/{lessonId}/complete")
    public ResponseEntity<Void> completeLesson(

            @PathVariable Long lessonId,

            @RequestHeader(HeaderConstants.USER_ID)
            Long userId,

            @RequestHeader(HeaderConstants.USER_ROLE)
            String role,

            @RequestHeader(HeaderConstants.INTERNAL_CALL)
            String internal) {

        UserRequestContext context =
                buildContext(userId, role, internal);

        courseProgressService.completeLesson(
                context,
                lessonId);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<CourseProgressResponseDTO>
    getCourseProgress(

            @PathVariable Long courseId,

            @RequestHeader(HeaderConstants.USER_ID)
            Long userId,

            @RequestHeader(HeaderConstants.USER_ROLE)
            String role,

            @RequestHeader(HeaderConstants.INTERNAL_CALL)
            String internal) {

        UserRequestContext context =
                buildContext(userId, role, internal);

        return ResponseEntity.ok(
                courseProgressService
                        .getCourseProgress(
                                context,
                                courseId));
    }

    @GetMapping("/{courseId}/modules/{moduleId}")
    public ResponseEntity<ModuleProgressResponseDTO>
    getModuleProgress(

            @PathVariable Long courseId,

            @PathVariable Long moduleId,

            @RequestHeader(HeaderConstants.USER_ID)
            Long userId,

            @RequestHeader(HeaderConstants.USER_ROLE)
            String role,

            @RequestHeader(HeaderConstants.INTERNAL_CALL)
            String internal) {

        UserRequestContext context =
                buildContext(userId, role, internal);

        return ResponseEntity.ok(
                courseProgressService
                        .getModuleProgress(
                                context,
                                courseId,
                                moduleId));
    }
}