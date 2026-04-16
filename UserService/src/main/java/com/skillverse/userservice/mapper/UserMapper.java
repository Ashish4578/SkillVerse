package com.skillverse.userservice.mapper;

import com.skillverse.userservice.dto.request.CreateUserRequest;
import com.skillverse.userservice.dto.request.UpdateUserRequest;
import com.skillverse.userservice.dto.response.UserResponseDTO;
import com.skillverse.userservice.entity.UserProfile;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {

    // Entity → Response DTO
    UserResponseDTO toDTO(UserProfile user);

    // Create DTO → Entity
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enabled", constant = "true")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    UserProfile toEntity(CreateUserRequest request);

    // Update existing entity
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    void updateUserFromDto(UpdateUserRequest request, @MappingTarget UserProfile user);
}