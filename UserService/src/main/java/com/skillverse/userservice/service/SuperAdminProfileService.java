package com.skillverse.userservice.service;

import java.util.List;

import com.skillverse.userservice.dto.AppUserRequestDTO;
import com.skillverse.userservice.dto.AppUserResponseDTO;

//SuperAdminProfileService.java
public interface SuperAdminProfileService {

	// Create any Profile like user, creator and admins
	AppUserResponseDTO updateAnyProfile(AppUserRequestDTO dto);

	// Delete any Profile
	String deleteAnyProfile(Long id);

	// Get list of all Users
	List<AppUserResponseDTO> getAllUsers();

	// Get list of all creator
	List<AppUserResponseDTO> getAllCreators();

	// Get list of all admins
	List<AppUserResponseDTO> getAllAdmins();
}