package com.skillverse.userservice.service;

import com.skillverse.userservice.dto.request.RegisterProfile;
import com.skillverse.userservice.dto.response.UserResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SuperAdminService {

    Page<UserResponseDTO> getAllUsers(Pageable pageable);

    void deleteUserByAdmin(Long userId);

    UserResponseDTO createAdmin(RegisterProfile request);

    void assignRole(Long userId, String role);
}
