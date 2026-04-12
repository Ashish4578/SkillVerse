package com.skillverse.userservice.service;

import com.skillverse.userservice.dto.request.ChangePasswordRequest;
import com.skillverse.userservice.dto.request.UpdateProfileData;
import com.skillverse.userservice.dto.response.UserResponseDTO;

public interface UserService {
    // 🔹 Self (User / Creator / Admin / SuperAdmin)
    UserResponseDTO getMyProfile();

    UserResponseDTO updateMyProfile(UpdateProfileData data);

    void deleteMyProfile();

    public void changePassword(ChangePasswordRequest request);

}
