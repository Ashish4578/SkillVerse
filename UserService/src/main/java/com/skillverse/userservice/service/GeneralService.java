package com.skillverse.userservice.service;

import java.util.Optional;

import com.skillverse.userservice.dto.AppUserResponseDTO;

public interface GeneralService {
	// Find Profile Id by User Name
	Optional<Long> findByProfileIdByUserName(String username);

	// Find Id by Contact Number
	Optional<Long> findProfileIdByContactNumber(String contactNumber);

	// Find id By email
	Optional<Long> findProfileIdByEmail(String email);


	// Find Profile by Id
	AppUserResponseDTO findProfileById(Long id);

	// Find By User Name
	AppUserResponseDTO findByUsername(String username);

	// Find By Email
	AppUserResponseDTO findByEmail(String email);

	// Find by Contact Number
	AppUserResponseDTO findByContact(String contactNumber);

	// Check whether User Name exists or Not
	boolean existsByUsername(String username);

	// Send notifications by User Name
	String sendMailByUsername(String username);

	// Send notifications by id
	String sendMailByUserId(Long userId);

	// Authenticate Profile
	AppUserResponseDTO authenticate(String username, String password);

}
