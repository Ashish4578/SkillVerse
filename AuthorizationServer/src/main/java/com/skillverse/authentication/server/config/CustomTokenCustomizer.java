package com.skillverse.authentication.server.config;

import org.springframework.stereotype.Component;

@Component
public class CustomTokenCustomizer {
//        implements OAuth2TokenCustomizer<JwtEncodingContext> {

//    @Autowired
//    private UserServiceClient userServiceClient;
//
//    @Override
//    public void customize(JwtEncodingContext context) {
//        String username = context.getPrincipal().getName();
//
//        // Step 1: Try to find the user
//        UserResponse user = userServiceClient.findUserByUsername(username)
//                .blockOptional()
//                .orElseGet(() -> {
//                    // Step 2: Register user if not found
//                    return userServiceClient.registerUser(username).block();
//                });
//
//        // Step 3: Add roles to token
//        if (user != null && user.getRole() != null) {
//            context.getClaims().claim("role", user.getRole());
//        }
//    }
}
