package com.skillverse.userservice.controller;

import com.skillverse.userservice.dto.AppUserResponseDTO;
import com.skillverse.userservice.dto.UpdateProfileData;
import com.skillverse.userservice.entity.AppUser;
import com.skillverse.userservice.service.GeneralService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.skillverse.userservice.entity.TypesOfUser.CREATOR;
import static com.skillverse.userservice.entity.TypesOfUser.STUDENT;

@RestController
@Validated
@RequestMapping("skillverse/creator")
@Slf4j
public class CreatorProfileController {

    @Autowired
    private GeneralService generalService;
	@GetMapping("/")
	public String getMsg() {
		return "Welcome to Creator Controller";
	}

    @PostMapping("login")
    public String getCreatorLogin(@Valid @RequestBody AppUser user) {
        return "creator successfully for user";
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<AppUserResponseDTO> getStudentProfile(@PathVariable @Valid Long id) {
        log.info("Getting Student Profile with id {}", id);
        AppUserResponseDTO responseDTO = generalService.findProfileById(id, CREATOR);
        return ResponseEntity.ok(responseDTO);
    }
    @PutMapping("/profile/{id}")
    public ResponseEntity<AppUserResponseDTO> updateOwnProfile(@PathVariable Long id, @RequestBody @Valid UpdateProfileData updateProfileData) {
        log.info("Updating Student Profile");
        AppUserResponseDTO responseDTO = generalService.updateOwnProfile(id, updateProfileData,CREATOR);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/profile/{id}")
    public ResponseEntity<AppUserResponseDTO> deleteOwnProfile(@PathVariable @Valid Long id) {
        log.info("Deleting Student Profile with id ");
        generalService.deleteOwnProfile(id,CREATOR);
        return ResponseEntity.noContent().build();
    }
}
