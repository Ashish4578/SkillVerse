package com.skillverse.userservice.service.impl;

import com.skillverse.userservice.dto.request.UpdateUserRequest;
import com.skillverse.userservice.dto.response.UserResponseDTO;
import com.skillverse.userservice.entity.UserProfile;
import com.skillverse.userservice.entity.UserRequestContext;
import com.skillverse.userservice.exception.DuplicateUserException;
import com.skillverse.userservice.exception.ResourceNotFoundException;
import com.skillverse.userservice.mapper.UserMapper;
import com.skillverse.userservice.repository.UserRepository;
import com.skillverse.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional(readOnly = true)
    @Override
    public UserResponseDTO getMyProfile(UserRequestContext context) {

        Long userId = context.getUserId();

        log.info("UserService :: getMyProfile userId={} role={}", userId, context.getRole());

        UserProfile user = getUserOrThrow(userId);

        return userMapper.toDTO(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponseDTO getUserById(Long userId) {
        log.info("Fetching user internally userId={}", userId);

        UserProfile user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return userMapper.toDTO(user);
    }

    @Transactional
    @Override
    public UserResponseDTO updateMyProfile(UserRequestContext context, UpdateUserRequest request) {

        Long userId = context.getUserId();

        log.info("UserService :: updateMyProfile userId={} role={}", userId, context.getRole());

        UserProfile user = getUserOrThrow(userId);

        // Email uniqueness check
        if (request.getEmail() != null && !request.getEmail().equals(user.getEmail())) {
            if (userRepository.existsByEmail(request.getEmail())) {
                throw new DuplicateUserException("Email already exists");
            }
        }

        userMapper.updateUserFromDto(request, user);

        return userMapper.toDTO(userRepository.save(user));
    }

    @Transactional
    @Override
    public void deleteMyProfile(UserRequestContext context) {

        Long userId = context.getUserId();

        log.info("UserService :: deleteMyProfile userId={} role={}", userId, context.getRole());

        UserProfile user = getUserOrThrow(userId);

        userRepository.delete(user);
    }

    // Common helper
    private UserProfile getUserOrThrow(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }
}