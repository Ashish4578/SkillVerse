package com.skillverse.userservice.controller;

import com.skillverse.userservice.dto.AppUserRequestDTO;
import com.skillverse.userservice.dto.AppUserResponseDTO;
import com.skillverse.userservice.dto.UpdateProfileData;
import com.skillverse.userservice.service.StudentProfileService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("skillverse/student")
@Validated // Crucial for validating @PathVariable and @RequestParam
@Slf4j
public class StudentServiceProfileController {

    @Autowired
    private StudentProfileService studentProfileService;

    @GetMapping
    public String getWelcomeMsg() {
        return "Welcome user Profile Service";
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<AppUserResponseDTO> getStudentProfile(@PathVariable @Valid Long id) {
        log.info("Getting Student Profile with id {}", id);
        AppUserResponseDTO responseDTO = studentProfileService.getStudentProfileById(id);
        return ResponseEntity.ok(responseDTO);
    }
    @PutMapping("/profile/{id}")
    public ResponseEntity<AppUserResponseDTO> updateOwnProfile(@PathVariable Long id, @RequestBody @Valid UpdateProfileData updateProfileData) {
        log.info("Updating Student Profile");
        AppUserResponseDTO responseDTO = studentProfileService.updateOwnProfile(id, updateProfileData);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/profile/{id}")
    public ResponseEntity<AppUserResponseDTO> deleteOwnProfile(@PathVariable @Valid Long id) {
        log.info("Deleting Student Profile with id ");
        studentProfileService.deleteOwnProfile(id);
        return ResponseEntity.noContent().build();
    }
}