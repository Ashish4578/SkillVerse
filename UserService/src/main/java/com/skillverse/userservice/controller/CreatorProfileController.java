package com.skillverse.userservice.controller;

import com.skillverse.userservice.entity.AppUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.skillverse.userservice.service.CreatorProfileService;

@RestController
@Validated
@RequestMapping("skillverse/creator")
public class CreatorProfileController {
	@Autowired
	private CreatorProfileService creatorProfileService;

	@GetMapping("/")
	public String getMsg() {
		return "Welcome to Creator Controller";
	}

    @PostMapping("login")
    public String getCreatorLogin(@Valid @RequestBody AppUser user) {
        return "creator successfully for user";
    }
}
