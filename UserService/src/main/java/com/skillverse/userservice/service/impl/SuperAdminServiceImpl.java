package com.skillverse.userservice.service.impl;

import com.skillverse.userservice.dto.request.CreateUserRequest;
import com.skillverse.userservice.dto.response.UserResponseDTO;
import com.skillverse.userservice.entity.UserProfile;
import com.skillverse.userservice.entity.UserRequestContext;
import com.skillverse.userservice.exception.DuplicateUserException;
import com.skillverse.userservice.exception.ResourceNotFoundException;
import com.skillverse.userservice.mapper.UserMapper;
import com.skillverse.userservice.repository.UserRepository;
import com.skillverse.userservice.service.SuperAdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SuperAdminServiceImpl implements SuperAdminService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional(readOnly = true)
    @Override
    public Page<UserResponseDTO> getAllUsers(UserRequestContext context, Pageable pageable) {

        log.info("SuperAdminService :: getAllUsers superAdminId={}", context.getUserId());

        return userRepository.findAll(pageable)
                .map(userMapper::toDTO);
    }

    @Transactional
    @Override
    public void deleteUser(UserRequestContext context, Long userId) {

        log.info("SuperAdminService :: deleteUser superAdminId={} targetUserId={}",
                context.getUserId(), userId);

        UserProfile user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        userRepository.delete(user);
    }

    @Transactional
    @Override
    public UserResponseDTO createAdmin(UserRequestContext context, CreateUserRequest request) {

        log.info("SuperAdminService :: createAdmin superAdminId={}", context.getUserId());

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new DuplicateUserException("Username already exists");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateUserException("Email already exists");
        }

        UserProfile user = userMapper.toEntity(request);

        return userMapper.toDTO(userRepository.save(user));
    }
}