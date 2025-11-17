package com.skillverse.userservice.controller;

import com.skillverse.userservice.dto.AppUserResponseDTO;
import com.skillverse.userservice.entity.AppUser;
import com.skillverse.userservice.entity.TypesOfUser;
import com.skillverse.userservice.service.GeneralService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.skillverse.userservice.entity.Role.ROLE_CREATOR;
import static com.skillverse.userservice.entity.Role.ROLE_STUDENT;
import static com.skillverse.userservice.entity.TypesOfUser.STUDENT;

@RestController
@Validated
@RequestMapping("skillverse/admin")
@Slf4j
public class AdminProfileController {

    @Autowired
    private GeneralService generalService;

	@GetMapping("/")
	public String getMsg() {
		return "Welcome to Admin Controller";
	}

    @PostMapping("login/admin")
    public String getAdminLogin(@Valid @RequestBody AppUser user) {
        return "login successfully for admin";
    }

    @GetMapping("/getAllStudents")
    public ResponseEntity<List<AppUser>> getAllStudents() {
        log.info("loading all students profile from controller");
        return new ResponseEntity(generalService.getAllUsersProfile(ROLE_STUDENT), HttpStatus.OK);
    }

    @GetMapping("/getAllCreators")
    public ResponseEntity<List<AppUserResponseDTO>> getAllCreators() {
        log.info("loading all creators profile from controller");
        return new ResponseEntity(generalService.getAllUsersProfile(ROLE_CREATOR), HttpStatus.OK);
    }
}
