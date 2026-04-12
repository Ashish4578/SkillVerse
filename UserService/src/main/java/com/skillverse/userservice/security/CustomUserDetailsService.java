package com.skillverse.userservice.security;

import com.skillverse.userservice.entity.AppUser;
import com.skillverse.userservice.entity.Permission;
import com.skillverse.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        AppUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // 🔹 Convert Role + Permission → Authorities
        Collection<GrantedAuthority> authorities = user.getRoles().stream()
                .flatMap(role -> {

                    String roleName = role.getName().startsWith("ROLE_")
                            ? role.getName()
                            : "ROLE_" + role.getName();

                    Stream<GrantedAuthority> roleStream =
                            Stream.of(new SimpleGrantedAuthority(roleName));

                    Stream<GrantedAuthority> permissionStream =
                            role.getPermissions().stream()
                                    .map(permission -> new SimpleGrantedAuthority(permission.getName()));

                    return Stream.concat(roleStream, permissionStream);
                })
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.isEnabled(),   // enabled
                true,               // accountNonExpired
                true,               // credentialsNonExpired
                true,               // accountNonLocked
                authorities
        );
    }
}
