package com.skillverse.userservice.service;

import com.skillverse.userservice.dto.request.CreateUserRequest;
import com.skillverse.userservice.dto.response.UserResponseDTO;
import com.skillverse.userservice.entity.UserRequestContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SuperAdminService {

    Page<UserResponseDTO> getAllUsers(UserRequestContext context, Pageable pageable);

    void deleteUser(UserRequestContext context, Long userId);

    UserResponseDTO createAdmin(UserRequestContext context, CreateUserRequest request);
}