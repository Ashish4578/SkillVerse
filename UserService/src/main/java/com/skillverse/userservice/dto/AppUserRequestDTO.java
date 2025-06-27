package com.skillverse.userservice.dto;

import java.util.HashSet;
import java.util.Set;

import com.skillverse.userservice.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AppUserRequestDTO {
	
	private String username;

	private String password;

	private boolean enabled;

	private String email;
	
	private String contactNumber;

	private Set<Role> roles = new HashSet<>();
}
