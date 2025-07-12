package com.skillverse.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
