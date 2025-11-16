package com.skillverse.userservice.service;

import com.skillverse.userservice.dto.AppUserRequestDTO;
import com.skillverse.userservice.dto.AppUserResponseDTO;
import com.skillverse.userservice.dto.UpdateProfileData;

public interface StudentProfileService {

	// Create User(own) profile
	AppUserResponseDTO createProfile(AppUserRequestDTO dto);

	// Update User(own) Profile
	AppUserResponseDTO updateOwnProfile(long id, UpdateProfileData updateProfileData);

	//  Delete User(own) Profile
	void deleteOwnProfile(Long id);

    AppUserResponseDTO getStudentProfileById(Long id);

}
