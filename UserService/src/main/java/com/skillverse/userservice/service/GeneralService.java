package com.skillverse.userservice.service;

import java.util.Optional;

import com.skillverse.userservice.dto.AppUserResponseDTO;
import com.skillverse.userservice.entity.Course;
import com.skillverse.userservice.entity.TypesOfUser;

public interface GeneralService {

    // Find Course List
    Optional<Course[]> getCourseList();

	// Find Profile Id by User Name
    Optional<Long> findProfileIdByUserName(String username);

	// Find Id by Contact Number
	Optional<Long> findProfileIdByContactNumber(String contactNumber);

	// Find id By email
	Optional<Long> findProfileIdByEmail(String email);

	// Find Profile by Id
	AppUserResponseDTO findProfileById(Long id);

    // Find Profile by Id by specified TypesOfUser
    AppUserResponseDTO findProfileById(Long id, TypesOfUser userType);

	// Find By User Name
	Optional<AppUserResponseDTO> findByUsername(String username);

	// Find By Email
	AppUserResponseDTO findProfileByEmail(String email);

	// Find by Contact Number
	AppUserResponseDTO findProfileByContactNumber(String contactNumber);

	// Check whether User Name exists or Not
	boolean existsByUsername(String username);

	// Send notifications by User Name
	Optional<String> sendMailByUsername(String username);

	// Send notifications by id
	Optional<String> sendMailByUserId(Long userId);

	// Authenticate Profile
	AppUserResponseDTO authenticate(String username, String password);

    // Reset user password by email or username
     void resetPassword(String identifier);

     // Change user password by id
     boolean changePassword(Long userId, String oldPassword, String newPassword);

     // Validate password strength / policy
     boolean validatePassword(String password);
}
