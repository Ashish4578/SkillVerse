package com.skillverse.userservice.service;

import java.util.List;

import com.skillverse.userservice.dto.AppUserRequestDTO;
import com.skillverse.userservice.dto.AppUserResponseDTO;

public interface CreatorProfileService {
	// Create Creator(own) Profile
	AppUserResponseDTO createCreatorProfile(AppUserRequestDTO dto);

	// Update Creator(own) Profile
	AppUserResponseDTO updateCreatorProfile(AppUserRequestDTO dto);

	// Delete(own) Creator Profile
	String deleteCreatorProfile(Long id);

	// Get List of Users that enroll Creator profile
	List<AppUserResponseDTO> getEnrolledUsers(Long creatorId);
}