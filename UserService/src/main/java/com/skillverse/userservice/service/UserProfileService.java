package com.skillverse.userservice.service;

import com.skillverse.userservice.dto.AppUserRequestDTO;
import com.skillverse.userservice.dto.AppUserResponseDTO;
import com.skillverse.userservice.entity.Role;

public interface UserProfileService {

	// Create User(own) profile
	AppUserResponseDTO createProfile(AppUserRequestDTO dto);

	// Update User(own) Profile
	AppUserResponseDTO updateOwnProfile(AppUserRequestDTO dto);

	//  Delete User(own) Profile
	void deleteOwnProfile(Long id);

}
