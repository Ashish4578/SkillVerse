package com.skillverse.ratingservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ratings")
public class RatingServiceController {
	@GetMapping
	public String msg() {
		return "Hello World from Ratings Service";
	}
	
}
