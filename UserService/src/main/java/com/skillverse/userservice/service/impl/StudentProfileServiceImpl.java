package com.skillverse.userservice.service.impl;

import static com.skillverse.userservice.mapper.UserServiceMapper.getConvertAppUserRequestDTOToAppUser;
import static com.skillverse.userservice.mapper.UserServiceMapper.getConvertAppUserToResponse;

import com.skillverse.userservice.controller.PublicController;
import com.skillverse.userservice.dto.UpdateProfileData;
import com.skillverse.userservice.entity.Course;
import com.skillverse.userservice.entity.TypesOfUser;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillverse.userservice.dto.AppUserRequestDTO;
import com.skillverse.userservice.dto.AppUserResponseDTO;
import com.skillverse.userservice.entity.AppUser;
import com.skillverse.userservice.repository.SkillVerseUserRepository;
import com.skillverse.userservice.service.GeneralService;
import com.skillverse.userservice.service.StudentProfileService;

import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;
import static com.skillverse.userservice.entity.TypesOfUser.STUDENT;

@Service
@Slf4j
public class StudentProfileServiceImpl implements StudentProfileService {

    @Autowired
    private GeneralService generalService;
    @Autowired
    private SkillVerseUserRepository skillVerseUserRepository;

    @Override
    public AppUserResponseDTO createProfile(AppUserRequestDTO dto) {
        AppUser appUser = skillVerseUserRepository.save(getConvertAppUserRequestDTOToAppUser(dto));
        return getConvertAppUserToResponse(appUser);
    }

    @Override
    public AppUserResponseDTO updateOwnProfile(long id, UpdateProfileData updateProfileData) {

		// Fetch user entity or throw exception
		AppUser existingUser = skillVerseUserRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("User ID not found: " + id));

		// Update fields
		existingUser.setUsername(updateProfileData.getUsername());
		existingUser.setContactNumber(updateProfileData.getContactNumber());
		existingUser.setEmail(updateProfileData.getEmail());
		existingUser.setEnabled(updateProfileData.isEnabled());

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

    @Override
    public AppUserResponseDTO getStudentProfileById(Long id) {
        log.info("Getting called General Service for Student Profile with id {}", id);
        return generalService.findProfileById(id);
    }
}
