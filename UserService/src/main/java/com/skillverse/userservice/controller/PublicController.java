package com.skillverse.userservice.controller;

import com.skillverse.userservice.dto.AppUserRequestDTO;
import com.skillverse.userservice.entity.Course;
import com.skillverse.userservice.entity.LoginUser;
import com.skillverse.userservice.entity.RegisterUser;
import com.skillverse.userservice.service.*;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Optional;

import static com.skillverse.userservice.entity.Role.*;
import static com.skillverse.userservice.entity.TypesOfUser.CREATOR;
import static com.skillverse.userservice.entity.TypesOfUser.STUDENT;

@RestController
@Validated
@RequestMapping("skillverse")
@Slf4j
public class PublicController {

    @Autowired
    private GeneralService generalService;

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
        log.info("Received registration request for user");
        if (!registerUser.getPassword().equals(registerUser.getConfirmPassword())) {
            return new ResponseEntity<>("Passwords do not match with Confirm Password ", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        switch (registerUser.getRole()) {
            case STUDENT:
                AppUserRequestDTO studentProfile = new AppUserRequestDTO(registerUser.getUsername(), registerUser.getPassword(),
                        true, registerUser.getEmail(), registerUser.getPhone(), Collections.singleton(ROLE_STUDENT));
                generalService.createProfile(studentProfile, STUDENT);
                log.info("Profile is created : Student");
                return ResponseEntity.ok("Student registered successfully");

            case CREATOR:
                AppUserRequestDTO creatorProfile = new AppUserRequestDTO(registerUser.getUsername(), registerUser.getPassword(),
                        true, registerUser.getEmail(), registerUser.getPhone(), Collections.singleton(ROLE_CREATOR));
                generalService.createProfile(creatorProfile, CREATOR);
                log.info("Profile is created : Creator");
                return ResponseEntity.ok("Creator registered successfully");

            default:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
