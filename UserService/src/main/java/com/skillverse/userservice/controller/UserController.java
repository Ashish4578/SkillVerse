package com.skillverse.userservice.controller;

import com.skillverse.userservice.dto.request.ChangePasswordRequest;
import com.skillverse.userservice.dto.request.UpdateProfileData;
import com.skillverse.userservice.dto.response.UserResponseDTO;
import com.skillverse.userservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
@Validated
public class UserController {

    private final UserService userService;

    // 🔹 Get my profile
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/me")
    public UserResponseDTO getMyProfile() {
        log.info("UserController :: getMyProfile()");
        return userService.getMyProfile();
    }

    // 🔹 Update my profile
    @PreAuthorize("isAuthenticated()")
    @PutMapping("/me")
    public UserResponseDTO updateMyProfile(@Valid @RequestBody UpdateProfileData data) {
        log.info("UserController :: updateMyProfile()");
        return userService.updateMyProfile(data);
    }

    // 🔹 Delete my profile
    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/me")
    public void deleteMyProfile() {
        log.info("UserController :: deleteMyProfile()");
        userService.deleteMyProfile();
    }

    // 🔹 Change password
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/change-password")
    public void changePassword(@Valid @RequestBody ChangePasswordRequest request) {
        log.info("UserController :: changePassword()");
        userService.changePassword(request);
    }
}