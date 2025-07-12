package com.skillverse.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
