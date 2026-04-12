package com.skillverse.userservice.controller;

import com.skillverse.userservice.entity.AppUser;
import com.skillverse.userservice.repository.UserRepository;
import com.skillverse.userservice.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")   // better grouping
public class TokenGenerator {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @GetMapping("/test-token")
    public String generateTestToken(
            @RequestParam String username
    ) {

        AppUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<String> authorities = new ArrayList<>();

        // roles
        user.getRoles().forEach(role ->
                authorities.add(
                        role.getName().startsWith("ROLE_")
                                ? role.getName()
                                : "ROLE_" + role.getName()
                )
        );

        // permissions
        user.getRoles().forEach(role ->
                role.getPermissions().forEach(permission ->
                        authorities.add(permission.getName())
                )
        );

        return jwtUtil.generateToken(user.getUsername(), authorities);
    }
}