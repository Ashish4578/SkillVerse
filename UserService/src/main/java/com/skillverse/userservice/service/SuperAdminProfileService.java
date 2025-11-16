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

    //Create admin profile
    AppUserResponseDTO createAdminProfile(AppUserRequestDTO appUserRequestDTO);

	// Get list of all Users
	List<AppUserResponseDTO> getAllUsers();

	// Get list of all creator
	List<AppUserResponseDTO> getAllCreators();

	// Get list of all admins
	List<AppUserResponseDTO> getAllAdmins();

    // Assign role to user
    void assignRoleToUser(Long userId, String roleName);

     // Remove role from user
     void removeRoleFromUser(Long userId, String roleName);

     // List roles of a user
     List<String> getUserRoles(Long userId);

     // Define new role or update role permissions
     void createOrUpdateRole(String roleName, List<String> permissions);
}