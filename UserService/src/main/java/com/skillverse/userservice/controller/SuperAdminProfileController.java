package com.skillverse.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
