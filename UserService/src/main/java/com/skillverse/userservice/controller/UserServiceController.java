package com.skillverse.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillverse.userservice.entity.AppUser;
import com.skillverse.userservice.entity.UserResponse;
import com.skillverse.userservice.service.SkillVerseUserService;

@RestController
@RequestMapping("/users")
public class UserServiceController {
	@Autowired
	private SkillVerseUserService skillVerseUserService;

	@GetMapping
	public String msg() {
		return "Hello World from User Service";
	}

	// Get Profile by UserName
	@GetMapping("/{username}")
	public ResponseEntity<UserResponse> getUser(@PathVariable("username") String userName) {
		UserResponse userResponse = skillVerseUserService.findProfileByUserName(userName);
		return new ResponseEntity<>(userResponse, HttpStatus.OK);
	}

	// Register Profile
	@PostMapping
	public ResponseEntity<AppUser> registerProfile(@RequestBody AppUser appUser) {
		if (appUser.getUsername() == null || appUser.getUsername().isBlank()) {
			return ResponseEntity.badRequest().build();
		}
		return new ResponseEntity<>(skillVerseUserService.createProfile(appUser), HttpStatus.CREATED);
	}

	// Update Profile
	@PutMapping
	public ResponseEntity<AppUser> updateProfile(@RequestBody AppUser appUser) {
		return new ResponseEntity<>(skillVerseUserService.updateProfileData(appUser), HttpStatus.ACCEPTED);
	}

	// Delete Profile
	@DeleteMapping
	public ResponseEntity<String> deleteProfile(@RequestBody AppUser deleteUser) {
		skillVerseUserService.deleteProfile(deleteUser);
		return new ResponseEntity<>("Profile Deleted Successfully",HttpStatus.ACCEPTED);
	}

}
