package com.skillverse.userservice.service;

import com.skillverse.userservice.dto.response.UserResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminService {
    Page<UserResponseDTO> getAllManagedUsers(Pageable pageable);
    void deleteUser(Long userId);
}
