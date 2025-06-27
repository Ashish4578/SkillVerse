package com.skillverse.userservice.dto;

import java.util.HashSet;
import java.util.Set;

import com.skillverse.userservice.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUserResponseDTO {
	private String username;
	private String email;
	private String contactNumber;
	private boolean enabled;
	private Set<Role> roles = new HashSet<>();
}
