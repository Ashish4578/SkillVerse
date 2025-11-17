package com.skillverse.userservice.controller;

import com.skillverse.userservice.dto.AppUserResponseDTO;
import com.skillverse.userservice.dto.UpdateProfileData;
import com.skillverse.userservice.service.GeneralService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.skillverse.userservice.entity.TypesOfUser.STUDENT;

@RestController
@RequestMapping("skillverse/student")
@Validated // Crucial for validating @PathVariable and @RequestParam
@Slf4j
public class StudentServiceProfileController {

    @Autowired
    private GeneralService generalService;

    @GetMapping
    public String getWelcomeMsg() {
        return "Welcome user Profile Service";
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<AppUserResponseDTO> getStudentProfile(@PathVariable @Valid Long id) {
        log.info("Getting Student Profile with id {}", id);
        return ResponseEntity.ok(generalService.findProfileById(id, STUDENT));
    }
    @PutMapping("/profile/{id}")
    public ResponseEntity<AppUserResponseDTO> updateOwnProfile(@PathVariable Long id, @RequestBody @Valid UpdateProfileData updateProfileData) {
        log.info("Updating Student Profile");
        return ResponseEntity.ok(generalService.updateOwnProfile(id, updateProfileData,STUDENT));
    }

    @DeleteMapping("/profile/{id}")
    public ResponseEntity<AppUserResponseDTO> deleteOwnProfile(@PathVariable @Valid Long id) {
        log.info("Deleting Student Profile with id ");
        generalService.deleteOwnProfile(id, STUDENT);
        return ResponseEntity.noContent().build();
    }
}