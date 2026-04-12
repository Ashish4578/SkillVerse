package com.skillverse.userservice.security;

import com.skillverse.userservice.entity.AppUser;
import com.skillverse.userservice.exception.ResourceNotFoundException;
import com.skillverse.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserSecurityHelper {

    private final UserRepository userRepository;

    public AppUser getCurrentUser() {
        String username = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    public boolean hasRole(AppUser user, String role) {
        return user.getRoles().stream()
                .anyMatch(r -> r.getName().equals(role));
    }
}
