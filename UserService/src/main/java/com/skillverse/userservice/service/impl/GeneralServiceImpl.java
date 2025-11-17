package com.skillverse.userservice.service.impl;

import java.util.List;
import java.util.Optional;

import com.skillverse.userservice.dto.AppUserRequestDTO;
import com.skillverse.userservice.dto.UpdateProfileData;
import com.skillverse.userservice.entity.AppUser;
import com.skillverse.userservice.entity.Course;
import com.skillverse.userservice.entity.Role;
import com.skillverse.userservice.entity.TypesOfUser;
import com.skillverse.userservice.mapper.UserServiceMapper;
import com.skillverse.userservice.repository.SkillVerseUserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillverse.userservice.dto.AppUserResponseDTO;
import com.skillverse.userservice.service.GeneralService;

import static com.skillverse.userservice.entity.TypesOfUser.STUDENT;
import static com.skillverse.userservice.mapper.UserServiceMapper.getConvertAppUserRequestDTOToAppUser;
import static com.skillverse.userservice.mapper.UserServiceMapper.getConvertAppUserToResponse;

@Service
@Slf4j
public class GeneralServiceImpl implements GeneralService {


    @Autowired
    private SkillVerseUserRepository skillVerseUserRepository;

    @Override
    public Optional<Course[]> getCourseList() {
        // 1. Create a few Course objects
        Course course1 = new Course();
        course1.setCourseId("CS101");
        course1.setCourseName("Introduction to Java");
        course1.setCoursePrice(49.99f);
        course1.setCourseCategory("CS101");
        course1.setCourseDescription("Introduction to Java");
        course1.setCourseImg("CS101");
        course1.setCourseCategory("Coding");
        course1.setCourseCreatorName("Ashihs");
        course1.setCourseCreatorPhone("9272373");
        course1.setCourseCreatorEmail("ashish@mail.com");

        Course course2 = new Course();
        course2.setCourseId("DB202");
        course2.setCourseName("Advanced SQL and Databases");
        course2.setCoursePrice(79.50f);
        course2.setCourseCategory("CS101");
        course2.setCourseDescription("Introduction to Pyhton");
        course2.setCourseImg("CS202");
        course2.setCourseCategory("Coding");
        course2.setCourseCreatorName("gadekar");
        course2.setCourseCreatorPhone("4272373");
        course2.setCourseCreatorEmail("gadekar@mail.com");

        // 2. Create an array of Course objects
        Course[] courseArray = {course1, course2};

        // 3. Wrap the array in an Optional and return it
        return Optional.of(courseArray);
    }

    // Fetch user profile by email (throws if absent)
    @Override
    public AppUserResponseDTO findProfileByEmail(String email) {
        AppUser appUser = skillVerseUserRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("No user found with email: " + email));
        return UserServiceMapper.getConvertAppUserToResponse(appUser);
    }

    // Fetch user profile by contact number (throws if absent)
    @Override
    public AppUserResponseDTO findProfileByContactNumber(String contactNumber) {
        AppUser appUser = skillVerseUserRepository.findByContactNumber(contactNumber)
                .orElseThrow(() -> new EntityNotFoundException("No user found with contact number: " + contactNumber));
        return UserServiceMapper.getConvertAppUserToResponse(appUser);
    }

    // Fetch profile ID by username (returns Optional<Long>)
    @Override
    public Optional<Long> findProfileIdByUserName(String username) {
        return skillVerseUserRepository.findAppUserIdByUsername(username);
    }

    // Fetch profile ID by contact number
    @Override
    public Optional<Long> findProfileIdByContactNumber(String contactNumber) {
        return skillVerseUserRepository.findAppUserIdByContactNumber(contactNumber);
    }

    // Fetch profile ID by email
    @Override
    public Optional<Long> findProfileIdByEmail(String email) {
        return skillVerseUserRepository.findAppUserIdByEmail(email);
    }

    // Fetch full profile by ID (throws if not found)
    @Override
    public AppUserResponseDTO findProfileById(Long id) {
        AppUser appUser = skillVerseUserRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No user found with ID: " + id));
        return UserServiceMapper.getConvertAppUserToResponse(appUser);
    }

    @Override
    public AppUserResponseDTO findProfileById(Long id, TypesOfUser userType) {
//        @Override
//        public AppUserResponseDTO findProfileById(Long id, TypesOfUser requesterType, boolean isOwnProfile) {
//            AppUser appUser = skillVerseUserRepository.findById(id)
//                    .orElseThrow(() -> new EntityNotFoundException("No user found with ID: " + id));
//
//            boolean canView = false;
//
//            if (isOwnProfile) {
//                // Always allow users to view their own profile
//                canView = true;
//            } else {
//                switch (requesterType) {
//                    case SUPER_ADMIN:
//                    case ADMIN:
//                        // Admins and super admins can view all profiles
//                        canView = true;
//                        break;
//                    case CREATOR:
//                    case STUDENT:
//                        // Other roles cannot view other users' profiles
//                        canView = false;
//                        break;
//                    default:
//                        throw new AccessDeniedException("Unknown user type");
//                }
//            }
//
//            if (!canView) {
//                throw new AccessDeniedException("You do not have permission to view this profile");
//            }
//
//            // Customize response based on profile ownership or requesterType if needed
//            if (isOwnProfile) {
//                return UserServiceMapper.toDetailedResponse(appUser); // full info
//            } else {
//                return UserServiceMapper.toPublicResponse(appUser, requesterType); // limited info
//            }
//        }

        return null;
    }

    @Override
    public AppUserResponseDTO createProfile(AppUserRequestDTO dto, TypesOfUser userType) {
        AppUser appUser = skillVerseUserRepository.save(getConvertAppUserRequestDTOToAppUser(dto));
        return getConvertAppUserToResponse(appUser);
    }

    @Override
    public AppUserResponseDTO updateOwnProfile(long id, UpdateProfileData updateProfileData, TypesOfUser userType) {
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
    public void deleteOwnProfile(Long id, TypesOfUser userType) {
        if (!skillVerseUserRepository.existsById(id)) {
            throw new EntityNotFoundException("User ID not found: " + id);
        }
        skillVerseUserRepository.deleteById(id);
    }

    @Override
    public List<AppUserResponseDTO> getAllUsersProfile(Role role) {
        AppUser[] appUser= skillVerseUserRepository.findAllUsersByTypeOfUser(role).orElseThrow().toArray(new AppUser[0]);
//    return  getConvertAppUserToResponse(appUser);
        return null;
    }

    // Fetch profile by username reusing findProfileById and ID fetch method
    @Override
    public Optional<AppUserResponseDTO> findByUsername(String username) {
        return skillVerseUserRepository.findAppUserIdByUsername(username)
                .map(this::findProfileById);
    }

    // Check if username exists (use repo method)
    @Override
    public boolean existsByUsername(String username) {
        return skillVerseUserRepository.existsByUsername(username);
    }

    // Return Optional email by username with logging
    @Override
    public Optional<String> sendMailByUsername(String username) {
        log.info("Searching email by username {}", username);
        return skillVerseUserRepository.findEmailByUsername(username)
                .filter(mail -> !mail.isBlank());
    }

    // Return Optional email by userId with logging
    @Override
    public Optional<String> sendMailByUserId(Long userId) {
        log.info("Searching email by userId {}", userId);
        return skillVerseUserRepository.findById(userId)
                .map(AppUser::getEmail)
                .filter(mail -> !mail.isBlank());
    }

    @Override
    public AppUserResponseDTO authenticate(String username, String password) {

        return null;
    }

    @Override
    public void resetPassword(String identifier) {

    }

    @Override
    public boolean changePassword(Long userId, String oldPassword, String newPassword) {
        return false;
    }

    @Override
    public boolean validatePassword(String password) {
        return false;
    }
}
