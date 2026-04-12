package com.skillverse.authentication.server.config;

import com.skillverse.authentication.server.entity.Permission;
import com.skillverse.authentication.server.entity.Role;
import com.skillverse.authentication.server.entity.User;
import com.skillverse.authentication.server.repo.PermissionRepository;
import com.skillverse.authentication.server.repo.RoleRepository;
import com.skillverse.authentication.server.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        if (userRepository.findByUsername("admin").isPresent()) {
            return;
        }

        // Permissions
        Permission read = permissionRepository.save(
                Permission.builder().name("READ_USER").build());

        Permission create = permissionRepository.save(
                Permission.builder().name("CREATE_USER").build());

        Permission update = permissionRepository.save(
                Permission.builder().name("UPDATE_USER").build());

        Permission delete = permissionRepository.save(
                Permission.builder().name("DELETE_USER").build());

        Permission updateProfile = permissionRepository.save(
                Permission.builder().name("UPDATE_PROFILE").build());

        // Roles
        Role admin = roleRepository.save(
                Role.builder()
                        .name("ROLE_ADMIN")
                        .permissions(Set.of(read, create, update, delete))
                        .build()
        );

        Role student = roleRepository.save(
                Role.builder()
                        .name("ROLE_STUDENT")
                        .permissions(Set.of(read, updateProfile))
                        .build()
        );

        Role superAdmin = roleRepository.save(
                Role.builder()
                        .name("ROLE_SUPERADMIN")
                        .permissions(Set.of(read, create, update, delete, updateProfile))
                        .build()
        );

        // User
        User user = User.builder()
                .username("admin")
                .email("admin@skillverse.com")
                .password(passwordEncoder.encode("password"))
                .enabled(true)
                .roles(Set.of(superAdmin))
                .build();

        userRepository.save(user);
    }
}