package com.skillverse.userservice.controller;

import com.skillverse.userservice.dto.request.UpdateUserRequest;
import com.skillverse.userservice.dto.response.UserResponseDTO;
import com.skillverse.userservice.entity.HeaderConstants;
import com.skillverse.userservice.entity.UserRequestContext;
import com.skillverse.userservice.exception.UnauthorizedException;
import com.skillverse.userservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
@Validated
public class UserController {

    private final UserService userService;
    private static final Set<String> ALLOWED_INTERNAL = Set.of("gateway", "internal");

    @GetMapping("/me")
    public ResponseEntity<UserResponseDTO> getMyProfile(
            @RequestHeader(HeaderConstants.USER_ID) Long userId,
            @RequestHeader(HeaderConstants.USER_ROLE) String role,
            @RequestHeader(HeaderConstants.INTERNAL_CALL) String internal) {

        validateInternalCall(internal);

        log.info("UserController :: getMyProfile userId={} role={}", userId, role);

        UserRequestContext context = new UserRequestContext(userId, role);

        return ResponseEntity.ok(userService.getMyProfile(context));
    }

    @PutMapping("/me")
    public ResponseEntity<UserResponseDTO> updateMyProfile(
            @RequestHeader(HeaderConstants.USER_ID) Long userId,
            @RequestHeader(HeaderConstants.USER_ROLE) String role,
            @RequestHeader(HeaderConstants.INTERNAL_CALL) String internal,
            @Valid @RequestBody UpdateUserRequest request) {

        validateInternalCall(internal);

        log.info("UserController :: updateMyProfile userId={} role={}", userId, role);

        UserRequestContext context = new UserRequestContext(userId, role);

        return ResponseEntity.ok(userService.updateMyProfile(context, request));
    }

    @DeleteMapping("/me")
    public ResponseEntity<Void> deleteMyProfile(
            @RequestHeader(HeaderConstants.USER_ID) Long userId,
            @RequestHeader(HeaderConstants.USER_ROLE) String role,
            @RequestHeader(HeaderConstants.INTERNAL_CALL) String internal) {

        validateInternalCall(internal);

        log.info("UserController :: deleteMyProfile userId={} role={}", userId, role);

        UserRequestContext context = new UserRequestContext(userId, role);

        userService.deleteMyProfile(context);
        return ResponseEntity.noContent().build();
    }

    private void validateInternalCall(String internal) {
        if (!"gateway".equals(internal)) {
            throw new UnauthorizedException("Unauthorized access");
        }
    }
    @GetMapping("/internal/{userId}")
    public ResponseEntity<UserResponseDTO> getUserInternal(
            @PathVariable Long userId,
            @RequestHeader(HeaderConstants.INTERNAL_CALL) String internal) {

        if (!ALLOWED_INTERNAL.contains(internal)) {
            throw new UnauthorizedException("Unauthorized");
        }

        return ResponseEntity.ok(userService.getUserById(userId));
    }
}