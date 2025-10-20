package com.skillverse.userservice.controller;

import com.skillverse.userservice.entity.AppUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.skillverse.userservice.service.SuperAdminProfileService;

@RestController
@Validated
@RequestMapping("skillverse/super-admin")
public class SuperAdminProfileController {

	@Autowired 
	private SuperAdminProfileService superAdminProfileService;
	
	@GetMapping("/")
	public String getMsg() {
		return "Welcome to Super Admin Controller";
	}

    @PostMapping("login")
    public String getSuperAdminLogin(@Valid @RequestBody AppUser user) {
        return "login successfully for super-admin";
    }
}
