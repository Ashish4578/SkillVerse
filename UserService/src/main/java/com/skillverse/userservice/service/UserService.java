package com.skillverse.userservice.service;

import com.skillverse.userservice.dto.request.UpdateUserRequest;
import com.skillverse.userservice.dto.response.UserResponseDTO;
import com.skillverse.userservice.entity.UserCreatedEvent;
import com.skillverse.userservice.entity.UserRequestContext;

public interface UserService {

    UserResponseDTO getMyProfile(UserRequestContext context);

    UserResponseDTO updateMyProfile(UserRequestContext context, UpdateUserRequest request);

    void deleteMyProfile(UserRequestContext context);

    UserResponseDTO getUserById(Long userId);

    //saving data from authService to user service
    String createUserFromAuthService(UserCreatedEvent userCreatedEvent);
}