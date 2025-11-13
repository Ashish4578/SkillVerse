package com.skillverse.userservice.controller;

import com.skillverse.userservice.dto.AppUserResponseDTO;
import com.skillverse.userservice.entity.AppUser;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.skillverse.userservice.service.StudentProfileService;

@RestController
@RequestMapping("skillverse/student")
@Validated // Crucial for validating @PathVariable and @RequestParam
public class StudentServiceProfileController {

    private static final Logger log = LoggerFactory.getLogger(StudentServiceProfileController.class);
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
}