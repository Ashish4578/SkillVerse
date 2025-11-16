package com.skillverse.userservice.controller;

import com.skillverse.userservice.dto.AppUserRequestDTO;
import com.skillverse.userservice.dto.AppUserResponseDTO;
import com.skillverse.userservice.entity.AppUser;
import com.skillverse.userservice.entity.RegisterUser;
import com.skillverse.userservice.service.CreatorProfileService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.skillverse.userservice.service.SuperAdminProfileService;

import java.util.Collections;

import static com.skillverse.userservice.entity.Role.ROLE_ADMIN;
import static com.skillverse.userservice.entity.Role.ROLE_CREATOR;

@RestController
@Validated
@RequestMapping("skillverse/super-admin")
@Slf4j
public class SuperAdminProfileController {

    @Autowired
    private SuperAdminProfileService superAdminProfileService;

    @GetMapping("/")
    public String getMsg() {
        return "Welcome to Super Admin Controller";
    }

    @PostMapping("login")
    public ResponseEntity<AppUserResponseDTO> getSuperAdminLogin(@Valid @RequestBody AppUserRequestDTO appUserRequestDTO) {
        AppUserResponseDTO appUserResponseDTO = new AppUserResponseDTO();
        return ResponseEntity.ok(appUserResponseDTO);
    }

    @PostMapping("/create-admin")
    public ResponseEntity<AppUserResponseDTO> createAdminProfile(@Valid @RequestBody RegisterUser registerUser) {
        AppUserRequestDTO adminProfile = new AppUserRequestDTO(registerUser.getUsername(), registerUser.getPassword(),
                true, registerUser.getEmail(), registerUser.getPhone(), Collections.singleton(ROLE_ADMIN));
        log.info("Creating Super Admin Profile");
        return new ResponseEntity<>(superAdminProfileService.createAdminProfile(adminProfile), HttpStatus.CREATED);
    }




}
