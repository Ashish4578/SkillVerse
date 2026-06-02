package com.skillverse.notificationservice.controller;

import com.skillverse.notificationservice.dto.response.NotificationResponseDTO;
import com.skillverse.notificationservice.exception.UnauthorizedException;
import com.skillverse.notificationservice.model.HeaderConstants;
import com.skillverse.notificationservice.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
@Slf4j
@Validated
public class NotificationController {

    private final NotificationService notificationService;

    private static final Set<String> ALLOWED_INTERNAL =
            Set.of("gateway");

    private void validateInternal(String internal) {

        if (!ALLOWED_INTERNAL.contains(internal)) {
            throw new UnauthorizedException(
                    "Unauthorized access");
        }
    }

    @GetMapping("/me")
    public ResponseEntity<Page<NotificationResponseDTO>>
    getMyNotifications(

            @RequestHeader(HeaderConstants.USER_ID)
            Long userId,

            @RequestHeader(HeaderConstants.INTERNAL_CALL)
            String internal,

            Pageable pageable) {

        validateInternal(internal);

        log.info(
                "NotificationController :: getMyNotifications userId={}",
                userId);

        return ResponseEntity.ok(
                notificationService.getMyNotifications(
                        userId,
                        pageable));
    }

    @GetMapping("/unread-count")
    public ResponseEntity<Long> getUnreadCount(

            @RequestHeader(HeaderConstants.USER_ID)
            Long userId,

            @RequestHeader(HeaderConstants.INTERNAL_CALL)
            String internal) {

        validateInternal(internal);

        log.info(
                "NotificationController :: getUnreadCount userId={}",
                userId);

        return ResponseEntity.ok(
                notificationService.getUnreadCount(userId));
    }

    @PutMapping("/{notificationId}/read")
    public ResponseEntity<Void> markAsRead(

            @PathVariable Long notificationId,

            @RequestHeader(HeaderConstants.INTERNAL_CALL)
            String internal) {

        validateInternal(internal);

        log.info(
                "NotificationController :: markAsRead notificationId={}",
                notificationId);

        notificationService.markAsRead(notificationId);

        return ResponseEntity.noContent().build();
    }
}
