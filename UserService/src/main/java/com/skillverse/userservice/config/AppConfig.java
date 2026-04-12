package com.skillverse.userservice.config;

import com.skillverse.userservice.entity.Permission;
import com.skillverse.userservice.entity.Role;
import com.skillverse.userservice.repository.RoleRepository;
import com.skillverse.userservice.service.PermissionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

import static com.skillverse.userservice.entity.ProfileOperations.*;
import static com.skillverse.userservice.entity.RoleType.*;

@Configuration
public class AppConfig {

    // 🔹 Password Encoder (MANDATORY for security)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 🔹 Optional: ObjectMapper (if custom JSON handling needed)
    // @Bean
    // public ObjectMapper objectMapper() {
    //     return new ObjectMapper();
    // }
    @Bean
    public AuthenticationProvider authenticationProvider(
            UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder
    ) {

        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);

        return authProvider;
    }


    @Bean
    CommandLineRunner initRoles(RoleRepository roleRepository) {
        return args -> {
            if (roleRepository.findByName(ROLE_STUDENT.name()).isEmpty()) {
                roleRepository.save(
                        Role.builder()
                                .name(ROLE_STUDENT.name())
                                .permissions(new HashSet<>())
                                .build()
                );
            }
            if (roleRepository.findByName(ROLE_CREATOR.name()).isEmpty()) {
                roleRepository.save(
                        Role.builder()
                                .name(ROLE_CREATOR.name())
                                .permissions(new HashSet<>())
                                .build()
                );
            }
            if (roleRepository.findByName(ROLE_ADMIN.name()).isEmpty()) {
                roleRepository.save(
                        Role.builder()
                                .name(ROLE_ADMIN.name())
                                .permissions(new HashSet<>())
                                .build()
                );
            }
            if (roleRepository.findByName(ROLE_SUPER_ADMIN.name()).isEmpty()) {
                roleRepository.save(
                        Role.builder()
                                .name(ROLE_SUPER_ADMIN.name())
                                .permissions(new HashSet<>())
                                .build()
                );
            }

        };
    }
    @Bean
    CommandLineRunner initPermissions(PermissionRepository permissionRepository) {
        return args -> {

            createPermissionIfNotExists(permissionRepository, READ_PROFILE.name());
            createPermissionIfNotExists(permissionRepository, UPDATE_PROFILE.name());
            createPermissionIfNotExists(permissionRepository, DELETE_PROFILE.name());
        };
    }

    private void createPermissionIfNotExists(PermissionRepository repo, String name) {
        repo.findByName(name)
                .orElseGet(() -> repo.save(
                        Permission.builder()
                                .name(name)
                                .build()
                ));
    }
    @Bean
    CommandLineRunner assignPermissionsToRoles(
            RoleRepository roleRepository,
            PermissionRepository permissionRepository
    ) {
        return args -> {

            Role userRole = roleRepository.findByName(ROLE_STUDENT.name())
                    .orElseThrow();

            Permission read = permissionRepository.findByName(READ_PROFILE.name()).orElseThrow();
            Permission update = permissionRepository.findByName(UPDATE_PROFILE.name()).orElseThrow();

            userRole.setPermissions(Set.of(read, update));
            roleRepository.save(userRole);

            Role adminRole = roleRepository.findByName(ROLE_ADMIN.name())
                    .orElseThrow();

            Permission delete = permissionRepository.findByName(DELETE_PROFILE.name()).orElseThrow();

            adminRole.setPermissions(Set.of(read, update, delete));
            roleRepository.save(adminRole);
        };
    }
}
