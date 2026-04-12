package com.skillverse.userservice.service.impl;

import com.skillverse.userservice.dto.request.ChangePasswordRequest;
import com.skillverse.userservice.dto.request.UpdateProfileData;
import com.skillverse.userservice.dto.response.UserResponseDTO;
import com.skillverse.userservice.entity.AppUser;
import com.skillverse.userservice.exception.PasswordUserException;
import com.skillverse.userservice.mapper.UserMapper;
import com.skillverse.userservice.repository.UserRepository;
import com.skillverse.userservice.security.UserSecurityHelper;
import com.skillverse.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserSecurityHelper helper;

    @Transactional(readOnly = true)
    @Override
    public UserResponseDTO getMyProfile() {
        log.info("UserServiceImpl:: getMyProfile");
        return userMapper.toDTO(helper.getCurrentUser());
    }

    @Transactional
    @Override
    public UserResponseDTO updateMyProfile(UpdateProfileData data) {
        log.info("UserServiceImpl:: updateMyProfile");
        AppUser user = helper.getCurrentUser();
       if (!user.getEmail().equals(data.getEmail()) &&
                userRepository.existsByEmail(data.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        user.setEmail(data.getEmail());
        user.setContactNumber(data.getContactNumber());

        return userMapper.toDTO(userRepository.save(user));
    }

    @Transactional
    @Override
    public void deleteMyProfile() {
        log.info("UserServiceImpl:: deleteMyProfile");
        AppUser user = helper.getCurrentUser();
        log.info("User {} deleted own account", user.getUsername());
        userRepository.delete(user);
    }

    @Transactional
    @Override
    public void changePassword(ChangePasswordRequest request) {
        log.info("UserServiceImpl:: changePassword");
        AppUser user = helper.getCurrentUser();

        if (request.getOldPassword() == null || request.getNewPassword() == null) {
            throw new PasswordUserException("Password cannot be null");
        }

        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            throw new PasswordUserException("Invalid old password");
        }

        if (request.getNewPassword().equals(request.getOldPassword())) {
            throw new PasswordUserException("New password must be different");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
    }
}