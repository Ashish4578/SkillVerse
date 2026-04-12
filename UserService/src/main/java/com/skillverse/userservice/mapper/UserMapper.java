package com.skillverse.userservice.mapper;

import com.skillverse.userservice.dto.request.RegisterProfile;
import com.skillverse.userservice.dto.response.UserResponseDTO;
import com.skillverse.userservice.entity.AppUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    // Entity → DTO
    UserResponseDTO toDTO(AppUser user);

    // DTO → Entity
    @Mapping(target = "roles", ignore = true)
    AppUser toEntity(RegisterProfile request);
}