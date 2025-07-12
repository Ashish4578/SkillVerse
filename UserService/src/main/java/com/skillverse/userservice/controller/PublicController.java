package com.skillverse.userservice.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("skillverse/")
public class PublicController {

	@GetMapping("/")
	public String getMsg() {
		return "Welcome to Public Controller";
	}
}
