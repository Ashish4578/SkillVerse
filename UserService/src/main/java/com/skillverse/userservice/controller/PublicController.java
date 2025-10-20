package com.skillverse.userservice.controller;

import com.skillverse.userservice.dto.AppUserRequestDTO;
import com.skillverse.userservice.entity.Course;
import com.skillverse.userservice.entity.LoginUser;
import com.skillverse.userservice.entity.RegisterUser;
import com.skillverse.userservice.service.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Optional;

import static com.skillverse.userservice.entity.Role.*;

@RestController
@Validated
@RequestMapping("skillverse")
public class PublicController {

    @Autowired
    private GeneralService generalService;

    @Autowired
    private CreatorProfileService creatorProfileService;

    @Autowired
    private SuperAdminProfileService superAdminProfileService;

    @Autowired
    private AdminProfileService adminProfileService;

    @Autowired
    private StudentProfileService studentProfileService;

    @GetMapping("/")
    public ResponseEntity<?> getCourseList() {
        Optional<Course[]> courseList = generalService.getCourseList();
        if (courseList.isPresent()) {
            return new ResponseEntity<>(courseList.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody RegisterUser registerUser) {

        if (!registerUser.getPassword().equals(registerUser.getConfirmPassword())) {
            return new ResponseEntity<>("Passwords do not match with Confirm Password ", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        switch (registerUser.getRole()) {
            case STUDENT:
                AppUserRequestDTO studentUser = new AppUserRequestDTO(registerUser.getUsername(), registerUser.getPassword(),
                        true, registerUser.getEmail(), registerUser.getPhone(), Collections.singleton(ROLE_STUDENT));
                studentProfileService.createProfile(studentUser);
                return ResponseEntity.ok("Student registered successfully");
            case CREATOR:
                AppUserRequestDTO creatorUser = new AppUserRequestDTO(registerUser.getUsername(), registerUser.getPassword(),
                        true, registerUser.getEmail(), registerUser.getPhone(), Collections.singleton(ROLE_CREATOR));
                creatorProfileService.createCreatorProfile(creatorUser);
                return ResponseEntity.ok("Creator registered successfully");
            default:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }


    }


    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@Valid @RequestBody LoginUser loginUser) {
        switch (loginUser.getTypesOfUser()) {
            case STUDENT:
//                student page
                return ResponseEntity.ok("Student login successfully");
            case CREATOR:
                // Creator page
                return ResponseEntity.ok("Creator login successfully");
            case ADMIN:
                // admin page
                return ResponseEntity.ok("Admin login successfully");
            // super admin page
            case SUPER_ADMIN:
                return ResponseEntity.ok("Super Admin login successfully");
            default:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/create-admin-profile")
    ResponseEntity<String> createAdminProfile(@Valid @RequestBody RegisterUser registerUser) {
        AppUserRequestDTO appUserRequestDTO = new AppUserRequestDTO(registerUser.getUsername(), registerUser.getPassword(),
                true, registerUser.getEmail(), registerUser.getPhone(), Collections.singleton(ROLE_ADMIN));
        superAdminProfileService.createAdminProfile(appUserRequestDTO);
        return ResponseEntity.ok("Admin login successfully");

    }
}
