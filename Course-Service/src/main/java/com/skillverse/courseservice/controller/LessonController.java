package com.skillverse.courseservice.controller;

import com.skillverse.courseservice.dto.request.LessonRequestDTO;
import com.skillverse.courseservice.dto.response.LessonResponseDTO;
import com.skillverse.courseservice.execption.UnauthorizedException;
import com.skillverse.courseservice.model.HeaderConstants;
import com.skillverse.courseservice.model.UserRequestContext;
import com.skillverse.courseservice.service.LessonService;
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
public class LessonController {

    private final LessonService lessonService;

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

    @PostMapping("/modules/{moduleId}/lessons")
    public ResponseEntity<LessonResponseDTO> createLesson(
            @PathVariable Long moduleId,
            @RequestHeader(HeaderConstants.USER_ID) Long userId,
            @RequestHeader(HeaderConstants.USER_ROLE) String role,
            @RequestHeader(HeaderConstants.INTERNAL_CALL) String internal,
            @Valid @RequestBody LessonRequestDTO request) {

        UserRequestContext context =
                buildContext(userId, role, internal);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(lessonService.createLesson(
                        context,
                        moduleId,
                        request));
    }

    @GetMapping("/modules/{moduleId}/lessons")
    public ResponseEntity<List<LessonResponseDTO>>
    getLessonsByModule(
            @PathVariable Long moduleId) {

        return ResponseEntity.ok(
                lessonService.getLessonsByModule(moduleId));
    }

    @PutMapping("/lessons/{lessonId}")
    public ResponseEntity<LessonResponseDTO> updateLesson(
            @PathVariable Long lessonId,
            @RequestHeader(HeaderConstants.USER_ID) Long userId,
            @RequestHeader(HeaderConstants.USER_ROLE) String role,
            @RequestHeader(HeaderConstants.INTERNAL_CALL) String internal,
            @Valid @RequestBody LessonRequestDTO request) {

        UserRequestContext context =
                buildContext(userId, role, internal);

        return ResponseEntity.ok(
                lessonService.updateLesson(
                        context,
                        lessonId,
                        request));
    }

    @DeleteMapping("/lessons/{lessonId}")
    public ResponseEntity<Void> deleteLesson(
            @PathVariable Long lessonId,
            @RequestHeader(HeaderConstants.USER_ID) Long userId,
            @RequestHeader(HeaderConstants.USER_ROLE) String role,
            @RequestHeader(HeaderConstants.INTERNAL_CALL) String internal) {

        UserRequestContext context =
                buildContext(userId, role, internal);

        lessonService.deleteLesson(
                context,
                lessonId);

        return ResponseEntity.noContent().build();
    }
}