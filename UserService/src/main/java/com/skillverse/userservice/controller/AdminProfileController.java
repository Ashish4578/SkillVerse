package com.skillverse.userservice.controller;

import com.skillverse.userservice.entity.AppUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.skillverse.userservice.service.AdminProfileService;

@RestController
@Validated
@RequestMapping("skillverse/admin")
public class AdminProfileController {
	
	@Autowired 
	private AdminProfileService adminProfileService;

	@GetMapping("/")
	public String getMsg() {
		return "Welcome to Admin Controller";
	}

    @PostMapping("login/admin")
    public String getAdminLogin(@Valid @RequestBody AppUser user) {
        return "login successfully for admin";
    }

}
