package com.skillverse.handle.enroll.cources.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {
	@GetMapping
	public String msg() {
		return "Hello World from Enrollments Service";
	}
	
}
