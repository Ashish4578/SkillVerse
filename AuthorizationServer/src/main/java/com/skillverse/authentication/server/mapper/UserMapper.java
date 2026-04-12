package com.skillverse.authentication.server.mapper;

import com.skillverse.authentication.server.dto.response.UserResponse;
import com.skillverse.authentication.server.entity.Role;
import com.skillverse.authentication.server.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "roles", expression = "java(mapRoles(user))")
    UserResponse toDto(User user);

    default Set<String> mapRoles(User user) {
        return user.getRoles()
                .stream()
                .map(Role::getName)
                .collect(Collectors.toSet());
    }
}