package com.skillverse.userservice.controller;

import com.skillverse.userservice.dto.request.RegisterProfile;
import com.skillverse.userservice.dto.response.UserResponseDTO;
import com.skillverse.userservice.service.SuperAdminService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/superadmin")
@RequiredArgsConstructor
@Slf4j
@Validated
public class SuperAdminController {

    private final SuperAdminService superAdminService;

    // 🔹 Get ALL users (including admins)
    @PreAuthorize("hasRole('SUPERADMIN')")
    @GetMapping("/users")
    public Page<UserResponseDTO> getAllUsers(Pageable pageable) {
        log.info("SuperAdminController :: getAllUsers()");
        return superAdminService.getAllUsers(pageable);
    }

    // 🔹 Delete ANY user
    @PreAuthorize("hasRole('SUPERADMIN')")
    @DeleteMapping("/users/{userId}")
    public void deleteUser(@PathVariable Long userId) {
            log.info("SuperAdminController :: deleteUser() with userId: ");
        superAdminService.deleteUserByAdmin(userId);
    }

    // 🔹 Create Admin
    @PreAuthorize("hasRole('SUPERADMIN')")
    @PostMapping("/admins")
    public UserResponseDTO createAdmin(@Valid @RequestBody RegisterProfile request) {
        log.info( "SuperAdminController:: createAdmin()" );
        return superAdminService.createAdmin(request);
    }

    // 🔹 Assign Role
    @PreAuthorize("hasRole('SUPERADMIN')")
    @PutMapping("/users/{userId}/roles")
    public void assignRole(@PathVariable Long userId,
                           @RequestParam @NotBlank String role){
        log.info("SuperAdminController :: assignRole" );
        superAdminService.assignRole(userId, role);
    }
}