package com.skillverse.userservice.service.impl;

import com.skillverse.userservice.dto.request.RegisterProfile;
import com.skillverse.userservice.dto.response.UserResponseDTO;
import com.skillverse.userservice.entity.AppUser;
import com.skillverse.userservice.entity.Role;
import com.skillverse.userservice.exception.DuplicateUserException;
import com.skillverse.userservice.exception.ResourceNotFoundException;
import com.skillverse.userservice.mapper.UserMapper;
import com.skillverse.userservice.repository.RoleRepository;
import com.skillverse.userservice.repository.UserRepository;
import com.skillverse.userservice.service.SuperAdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SuperAdminServiceImpl implements SuperAdminService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    @Override
    public Page<UserResponseDTO> getAllUsers(Pageable pageable) {
        log.info("Fetching all users for super admin");

        return userRepository.findAll(pageable)
                .map(userMapper::toDTO);
    }


    @Transactional
    @Override
    public void deleteUserByAdmin(Long userId) {
        log.info("Super admin deleting user with ID");
        AppUser user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        userRepository.delete(user);
    }

    @Transactional
    @Override
    public UserResponseDTO createAdmin(RegisterProfile request) {
        log.info("Super admin creating new user");
        // 🔹 Duplicate checks
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new DuplicateUserException("Username already exists");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateUserException("Email already exists");
        }

        AppUser user = userMapper.toEntity(request);

        Role role = roleRepository.findByName("ROLE_ADMIN")
                .orElseThrow(() -> new ResourceNotFoundException("Role not found"));

        user.getRoles().add(role);

        // 🔹 Encode password
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // 🔹 Enable user
        user.setEnabled(true);

        return userMapper.toDTO(userRepository.save(user));
    }

    @Transactional
    @Override
    public void assignRole(Long userId, String roleName) {
        log.info("Assigning role {} to user {}", roleName, userId);
        AppUser user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found"));

        user.getRoles().add(role);
        userRepository.save(user);
    }
}