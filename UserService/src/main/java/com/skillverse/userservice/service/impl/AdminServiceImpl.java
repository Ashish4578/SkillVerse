package com.skillverse.userservice.service.impl;

import com.skillverse.userservice.dto.response.UserResponseDTO;
import com.skillverse.userservice.entity.UserProfile;
import com.skillverse.userservice.entity.UserRequestContext;
import com.skillverse.userservice.exception.ResourceNotFoundException;
import com.skillverse.userservice.mapper.UserMapper;
import com.skillverse.userservice.repository.UserRepository;
import com.skillverse.userservice.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional(readOnly = true)
    @Override
    public Page<UserResponseDTO> getAllUsers(UserRequestContext context, Pageable pageable) {

        log.info("AdminService :: getAllUsers adminId={}", context.getUserId());

        return userRepository.findAll(pageable)
                .map(userMapper::toDTO);
    }

    @Transactional
    @Override
    public void deleteUser(UserRequestContext context, Long userId) {

        log.info("AdminService :: deleteUser adminId={} targetUserId={}",
                context.getUserId(), userId);

        UserProfile user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        userRepository.delete(user);
    }
}