package com.skillverse.userservice.controller;

import com.skillverse.userservice.dto.response.UserResponseDTO;
import com.skillverse.userservice.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admins")
@RequiredArgsConstructor
@Slf4j
@Validated
public class AdminController {

    private final AdminService adminService;

    // 🔹 Get all students & creators
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users")
    public Page<UserResponseDTO> getAllUsers(Pageable pageable) {
        log.info("Admin Controller :: getAllUsers()");
        return adminService.getAllManagedUsers(pageable);
    }

    // 🔹 Delete student or creator
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/users/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        log.info("Admin Controller :: deleteUser()");
        log.info("controller layer: deleting user with id: " + userId);
        adminService.deleteUser(userId);
    }
}