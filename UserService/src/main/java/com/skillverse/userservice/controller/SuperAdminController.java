package com.skillverse.userservice.controller;

import com.skillverse.userservice.dto.request.CreateUserRequest;
import com.skillverse.userservice.dto.response.UserResponseDTO;
import com.skillverse.userservice.entity.HeaderConstants;
import com.skillverse.userservice.entity.UserRequestContext;
import com.skillverse.userservice.exception.UnauthorizedException;
import com.skillverse.userservice.service.SuperAdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/super-admin")
@RequiredArgsConstructor
@Slf4j
@Validated
public class SuperAdminController {

    private final SuperAdminService superAdminService;

    @GetMapping("/users")
    public ResponseEntity<Page<UserResponseDTO>> getAllUsers(
            @RequestHeader(HeaderConstants.USER_ID) Long superAdminId,
            @RequestHeader(HeaderConstants.USER_ROLE) String role,
            @RequestHeader(HeaderConstants.INTERNAL_CALL) String internal,
            Pageable pageable) {

        validateInternalCall(internal);
        validateSuperAdmin(role);

        log.info("SuperAdminController :: getAllUsers superAdminId={} role={}", superAdminId, role);

        UserRequestContext context = new UserRequestContext(superAdminId, role);

        return ResponseEntity.ok(superAdminService.getAllUsers(context, pageable));
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Void> deleteUser(
            @RequestHeader(HeaderConstants.USER_ID) Long superAdminId,
            @RequestHeader(HeaderConstants.USER_ROLE) String role,
            @RequestHeader(HeaderConstants.INTERNAL_CALL) String internal,
            @PathVariable Long userId) {

        validateInternalCall(internal);
        validateSuperAdmin(role);

        log.info("SuperAdminController :: deleteUser superAdminId={} targetUserId={} role={}",
                superAdminId, userId, role);

        UserRequestContext context = new UserRequestContext(superAdminId, role);

        superAdminService.deleteUser(context, userId);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/admins")
    public ResponseEntity<UserResponseDTO> createAdmin(
            @RequestHeader(HeaderConstants.USER_ID) Long superAdminId,
            @RequestHeader(HeaderConstants.USER_ROLE) String role,
            @RequestHeader(HeaderConstants.INTERNAL_CALL) String internal,
            @Valid @RequestBody CreateUserRequest request) {

        validateInternalCall(internal);
        validateSuperAdmin(role);

        log.info("SuperAdminController :: createAdmin superAdminId={} role={}", superAdminId, role);

        UserRequestContext context = new UserRequestContext(superAdminId, role);

        return ResponseEntity.ok(superAdminService.createAdmin(context, request));
    }

    private void validateInternalCall(String internal) {
        if (!"gateway".equals(internal)) {
            throw new UnauthorizedException("Unauthorized access");
        }
    }

    private void validateSuperAdmin(String role) {
        if (!"SUPERADMIN".equals(role)) {
            throw new UnauthorizedException("Only SUPERADMIN can access this resource");
        }
    }
}