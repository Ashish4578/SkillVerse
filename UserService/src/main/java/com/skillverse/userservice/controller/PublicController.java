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

@RestController
@Validated
@RequestMapping("skillverse")
@Slf4j
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
        log.info("Received registration request for user");
        if (!registerUser.getPassword().equals(registerUser.getConfirmPassword())) {
            return new ResponseEntity<>("Passwords do not match with Confirm Password ", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        switch (registerUser.getRole()) {
            case STUDENT:
                AppUserRequestDTO studentUser = new AppUserRequestDTO(registerUser.getUsername(), registerUser.getPassword(),
                        true, registerUser.getEmail(), registerUser.getPhone(), Collections.singleton(ROLE_STUDENT));
                studentProfileService.createProfile(studentUser);
                log.info("Profile is created : Creator");
                return ResponseEntity.ok("Student registered successfully");

            case CREATOR:
                AppUserRequestDTO creatorUser = new AppUserRequestDTO(registerUser.getUsername(), registerUser.getPassword(),
                        true, registerUser.getEmail(), registerUser.getPhone(), Collections.singleton(ROLE_CREATOR));
                creatorProfileService.createCreatorProfile(creatorUser);
                log.info("Profile is created : Creator");
                return ResponseEntity.ok("Creator registered successfully");

            default:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }


    }


//    @PostMapping("/login")
//    public ResponseEntity<String> loginUser(@Valid @RequestBody LoginUser loginUser) {
//        switch (loginUser.getTypesOfUser()) {
//            case STUDENT:
//
//                return ResponseEntity.ok("Student login successfully");
//
//            case CREATOR:
//                // Creator page
//                return ResponseEntity.ok("Creator login successfully");
//
//            default:
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//        }
//    }


}
