package com.skillverse.userservice.service.impl;

import com.skillverse.userservice.dto.response.UserResponseDTO;
import com.skillverse.userservice.entity.AppUser;
import com.skillverse.userservice.exception.ResourceNotFoundException;
import com.skillverse.userservice.mapper.UserMapper;
import com.skillverse.userservice.repository.UserRepository;
import com.skillverse.userservice.security.UserSecurityHelper;
import com.skillverse.userservice.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.skillverse.userservice.entity.RoleType.ROLE_CREATOR;
import static com.skillverse.userservice.entity.RoleType.ROLE_STUDENT;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserSecurityHelper helper;

    @Override
    @Transactional(readOnly = true)
    public Page<UserResponseDTO> getAllManagedUsers(Pageable pageable) {

        List<String> roles = List.of(ROLE_STUDENT.name(), ROLE_CREATOR.name());

        Page<AppUser> users = userRepository.findUsersWithRoles(roles, pageable);

        return users.map(userMapper::toDTO);
    }

    @Transactional
    @Override
    public void deleteUser(Long userId) {
        log.info("Deleting user with id ");
        AppUser target = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (helper.hasRole(target, ROLE_STUDENT.name()) ||
                helper.hasRole(target, ROLE_CREATOR.name())) {

            userRepository.delete(target);
            return;
        }

        throw new AccessDeniedException("Admin cannot delete this user");
    }
}
