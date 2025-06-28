package com.skillverse.userservice.service;

import java.util.List;
import java.util.Map;

import com.skillverse.userservice.dto.AppUserResponseDTO;

public interface AdminProfileService {
	// Delete User Profile by UserId
	String deleteUserProfile(Long userId);

	// Delete Creator Profile by UserId
	String deleteCreatorProfile(Long creatorId);

	// Get all users
	List<AppUserResponseDTO> getAllUsersProfile();

	// Get List of all creator
	List<AppUserResponseDTO> getAllUsersByCreator(Long creatorId);

	// Get List of all creator and theirs subscribe users
	Map<AppUserResponseDTO, List<AppUserResponseDTO>> getAllCreatorsWithUsers();
}