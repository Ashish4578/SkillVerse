package com.skillverse.userservice.mapper;

import com.skillverse.userservice.dto.AppUserRequestDTO;
import com.skillverse.userservice.dto.AppUserResponseDTO;
import com.skillverse.userservice.entity.AppUser;

public class UserServiceMapper {

    public static AppUserResponseDTO getConvertAppUserToResponse(AppUser appUser) {
        return new AppUserResponseDTO(
                appUser.getUsername(),
                appUser.getEmail(),
                appUser.getContactNumber(),
                appUser.isEnabled(),
                appUser.getRoles()
        );
    }

    public static AppUser getConvertAppUserRequestDTOToAppUser(AppUserRequestDTO dto) {
        return new AppUser(
                dto.getUsername(),
                dto.getPassword(),
                dto.getEmail(),
                dto.getContactNumber(),
                dto.isEnabled(),
                dto.getRoles()
        );
    }
}
