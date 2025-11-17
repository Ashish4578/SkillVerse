package com.skillverse.userservice.controller;

import com.skillverse.userservice.dto.AppUserRequestDTO;
import com.skillverse.userservice.dto.AppUserResponseDTO;
import com.skillverse.userservice.entity.RegisterUser;
import com.skillverse.userservice.service.GeneralService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

import static com.skillverse.userservice.entity.Role.ROLE_ADMIN;
import static com.skillverse.userservice.entity.Role.ROLE_STUDENT;
import static com.skillverse.userservice.entity.TypesOfUser.ADMIN;
import static com.skillverse.userservice.entity.TypesOfUser.STUDENT;

@RestController
@Validated
@RequestMapping("skillverse/super-admin")
@Slf4j
public class SuperAdminProfileController {

    @Autowired
    private GeneralService generalService;

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
        log.info("Creating Admin Profile");
        AppUserRequestDTO adminProfile = new AppUserRequestDTO(registerUser.getUsername(), registerUser.getPassword(),
                true, registerUser.getEmail(), registerUser.getPhone(), Collections.singleton(ROLE_ADMIN));

        return new ResponseEntity<>(generalService.createProfile(adminProfile, ADMIN), HttpStatus.CREATED);
    }


}
