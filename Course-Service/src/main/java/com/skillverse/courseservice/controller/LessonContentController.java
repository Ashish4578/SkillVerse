package com.skillverse.courseservice.controller;

import com.skillverse.courseservice.DTO.request.LessonContentRequestDTO;
import com.skillverse.courseservice.DTO.response.LessonContentResponseDTO;
import com.skillverse.courseservice.execption.UnauthorizedException;
import com.skillverse.courseservice.model.HeaderConstants;
import com.skillverse.courseservice.model.UserRequestContext;
import com.skillverse.courseservice.service.LessonContentService;
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
public class LessonContentController {

    private final LessonContentService lessonContentService;
    private static final Set<String> ALLOWED_INTERNAL = Set.of("gateway");

    private UserRequestContext buildContext(
            @RequestHeader(HeaderConstants.USER_ID) Long userId,
            @RequestHeader(HeaderConstants.USER_ROLE) String role,
            @RequestHeader(HeaderConstants.INTERNAL_CALL) String internal) {

        if (!ALLOWED_INTERNAL.contains(internal)) {
            throw new UnauthorizedException("Unauthorized access");
        }
        return new UserRequestContext(userId, role);
    }

    @PostMapping("/lessons/{lessonId}/contents")
    public ResponseEntity<LessonContentResponseDTO> createContent(
            @PathVariable Long lessonId,
            @RequestHeader(HeaderConstants.USER_ID) Long userId,
            @RequestHeader(HeaderConstants.USER_ROLE) String role,
            @RequestHeader(HeaderConstants.INTERNAL_CALL) String internal,
            @Valid @RequestBody LessonContentRequestDTO request) {

        UserRequestContext context = buildContext(userId, role, internal);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(lessonContentService.createContent(context, lessonId, request));
    }

    @GetMapping("/lessons/{lessonId}/contents")
    public ResponseEntity<List<LessonContentResponseDTO>> getContentsByLesson(
            @PathVariable Long lessonId) {

        return ResponseEntity.ok(lessonContentService.getContentsByLesson(lessonId));
    }

    @PutMapping("/contents/{contentId}")
    public ResponseEntity<LessonContentResponseDTO> updateContent(
            @PathVariable Long contentId,
            @RequestHeader(HeaderConstants.USER_ID) Long userId,
            @RequestHeader(HeaderConstants.USER_ROLE) String role,
            @RequestHeader(HeaderConstants.INTERNAL_CALL) String internal,
            @Valid @RequestBody LessonContentRequestDTO request) {

        UserRequestContext context = buildContext(userId, role, internal);
        return ResponseEntity.ok(
                lessonContentService.updateContent(context, contentId, request));
    }

    @DeleteMapping("/contents/{contentId}")
    public ResponseEntity<Void> deleteContent(
            @PathVariable Long contentId,
            @RequestHeader(HeaderConstants.USER_ID) Long userId,
            @RequestHeader(HeaderConstants.USER_ROLE) String role,
            @RequestHeader(HeaderConstants.INTERNAL_CALL) String internal) {

        UserRequestContext context = buildContext(userId, role, internal);
        lessonContentService.deleteContent(context, contentId);
        return ResponseEntity.noContent().build();
    }
}