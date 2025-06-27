package com.skillverse.userservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.skillverse.userservice.dto.AppUserResponseDTO;
import com.skillverse.userservice.entity.AppUser;
import com.skillverse.userservice.entity.Role;


public interface SkillVerseUserService {

	// find userName
	Optional<Long> findProfileIdByUserName(AppUser appUser);

	// create a profile
	AppUser createProfile(AppUser user);

	// find user by profile id
	AppUserResponseDTO findUserByItsId(Long id);

	// find profile using User name
	AppUserResponseDTO findProfileByUserName(String username);

	// update the profile
	AppUser updateProfileData(AppUser updatedUser);

	// delete the profile by using id
	void deleteProfile(Long userId);
	
	// authentic profile
	AppUserResponseDTO authenticate(String username, String password, Role role);

	// Retrieve all data based on Role
	List<AppUserResponseDTO> getAllUsersByRole(Role role);

	// Pagination for retrieving data
	Page<AppUserResponseDTO> getUsers(Pageable pageable); 

	// Assigning the Role
	void assignRoleToUser(Long userId, Role role);
	
	// Removing Role
	void removeRoleFromUser(Long userId, Role role);

	// To make user distinct
	boolean existsByUsername(String username);

    
}
