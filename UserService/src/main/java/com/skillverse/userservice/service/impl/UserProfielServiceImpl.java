package com.skillverse.userservice.service.impl;

import static com.skillverse.userservice.mapper.UserServiceMapper.getConvertAppUserRequestDTOToAppUser;
import static com.skillverse.userservice.mapper.UserServiceMapper.getConvertAppUserToResponse;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillverse.userservice.dto.AppUserRequestDTO;
import com.skillverse.userservice.dto.AppUserResponseDTO;
import com.skillverse.userservice.entity.AppUser;
import com.skillverse.userservice.repository.SkillVerseUserRepository;
import com.skillverse.userservice.service.GeneralService;
import com.skillverse.userservice.service.UserProfileService;

import jakarta.persistence.EntityNotFoundException;;

@Service
public class UserProfielServiceImpl implements UserProfileService {

	@Autowired
	private GeneralService generalService;

	@Autowired
	private SkillVerseUserRepository skillVerseUserRepository;

	@Override
	public AppUserResponseDTO createProfile(AppUserRequestDTO dto) {
		return getConvertAppUserToResponse(skillVerseUserRepository.save(getConvertAppUserRequestDTOToAppUser(dto)));
	}

	@Override
	public AppUserResponseDTO updateOwnProfile(AppUserRequestDTO dto) {
		// Try finding ID from username, contact, or email
		Long id = generalService.findByProfileIdByUserName(dto.getUsername())
				.or(() -> generalService.findProfileIdByContactNumber(dto.getContactNumber()))
				.or(() -> generalService.findProfileIdByEmail(dto.getEmail()))
				.orElseThrow(() -> new RuntimeException("No matching profile found"));

		// Fetch user entity or throw exception
		AppUser existingUser = skillVerseUserRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("User ID not found: " + id));

		// Update fields
		existingUser.setUsername(dto.getUsername());
		existingUser.setContactNumber(dto.getContactNumber());
		existingUser.setEmail(dto.getEmail());
		existingUser.setEnabled(dto.isEnabled());
		existingUser.setRoles(dto.getRoles());

		// Save and return response
		AppUser updatedUser = skillVerseUserRepository.save(existingUser);
		return getConvertAppUserToResponse(updatedUser);
	}

	@Override
	public void deleteOwnProfile(Long id) {
		if (!skillVerseUserRepository.existsById(id)) {
			throw new EntityNotFoundException("User ID not found: " + id);
		}
		skillVerseUserRepository.deleteById(id);

	}

	
}
