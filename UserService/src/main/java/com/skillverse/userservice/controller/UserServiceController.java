package com.skillverse.userservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserServiceController {

	@GetMapping
	public String msg() {
		return "Hello World from User Service";
	}
	
}
