package com.skillverse.userservice.controller;

import com.skillverse.userservice.dto.response.UserResponseDTO;
import com.skillverse.userservice.entity.HeaderConstants;
import com.skillverse.userservice.entity.UserRequestContext;
import com.skillverse.userservice.exception.UnauthorizedException;
import com.skillverse.userservice.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/users")
@RequiredArgsConstructor
@Slf4j
@Validated
public class AdminController {

    private final AdminService adminService;

    @GetMapping
    public ResponseEntity<Page<UserResponseDTO>> getAllUsers(
            @RequestHeader(HeaderConstants.USER_ID) Long adminId,
            @RequestHeader(HeaderConstants.USER_ROLE) String role,
            @RequestHeader(HeaderConstants.INTERNAL_CALL) String internal,
            Pageable pageable) {

        validateInternalCall(internal);
        validateAdmin(role);

        log.info("AdminController :: getAllUsers adminId={} role={}", adminId, role);

        UserRequestContext context = new UserRequestContext(adminId, role);

        return ResponseEntity.ok(adminService.getAllUsers(context, pageable));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(
            @RequestHeader(HeaderConstants.USER_ID) Long adminId,
            @RequestHeader(HeaderConstants.USER_ROLE) String role,
            @RequestHeader(HeaderConstants.INTERNAL_CALL) String internal,
            @PathVariable Long userId) {

        validateInternalCall(internal);
        validateAdmin(role);

        log.info("AdminController :: deleteUser adminId={} targetUserId={} role={}",
                adminId, userId, role);

        UserRequestContext context = new UserRequestContext(adminId, role);

        adminService.deleteUser(context, userId);

        return ResponseEntity.noContent().build();
    }

    private void validateInternalCall(String internal) {
        if (!"gateway".equals(internal)) {
            throw new UnauthorizedException("Unauthorized access");
        }
    }

    private void validateAdmin(String role) {
        if (!"ADMIN".equals(role)) {
            throw new UnauthorizedException("Only ADMIN can access this resource");
        }
    }
}