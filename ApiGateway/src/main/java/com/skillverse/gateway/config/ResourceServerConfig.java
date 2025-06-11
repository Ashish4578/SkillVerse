package com.skillverse.gateway.config;

//@Configuration
//@EnableWebSecurity
public class ResourceServerConfig {

//    private final JwtEncoder jwtEncoder;
//
//    public ResourceServerConfig(JwtEncoder jwtEncoder) {
//        this.jwtEncoder = jwtEncoder;
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity
//            .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
//            .oauth2ResourceServer(oauth -> oauth.jwt(Customizer.withDefaults()));
//
//        return httpSecurity.build();
//    }
//
//    @Bean
//    public JwtEncoder jwtEncoder(RSAKey rsaKey) {
//        JWKSource<SecurityContext> jwkSource = new ImmutableJWKSet<>(new JWKSet(rsaKey));
//        return new NimbusJwtEncoder(jwkSource);
//    }
//
//    @Bean
//    public RSAKey rsaKey() {
//        KeyPair keyPair = generateRsaKey();
//        return new RSAKey.Builder((RSAPublicKey) keyPair.getPublic())
//                .privateKey((RSAPrivateKey) keyPair.getPrivate())
//                .keyID(UUID.randomUUID().toString())
//                .build();
//    }
//
//    private KeyPair generateRsaKey() {
//        try {
//            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
//            keyPairGenerator.initialize(2048);
//            return keyPairGenerator.generateKeyPair();
//        } catch (NoSuchAlgorithmException e) {
//            throw new IllegalStateException("RSA Key Pair generation failed", e);
//        }
//    }
//
//    // Token generation method (optional for a resource server, more useful for an auth server)
//    public String generateToken(String usJwtEncoder jwtEncoder;
//
//    public ResourceServerConfig(JwtEncoder jwtEncoder) {
//        this.jwtEncoder = jwtEncoder;
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity
//            .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
//            .oauth2ResourceServer(oauth -> oauth.jwt(Customizer.withDefaults()));
//
//        return httpSecurity.build();
//    }
//
//    @Bean
//    public JwtEncoder jwtEncoder(RSAKey rsaKey) {
//        JWKSource<SecurityContext> jwkSource = new ImmutableJWKSet<>(new JWKSet(rsaKey));
//        return new NimbusJwtEncoder(jwkSource);
//    }
//
//    @Bean
//    public RSAKey rsaKey() {
//        KeyPair keyPair = generateRsaKey();
//        return new RSAKey.Builder((RSAPublicKey) keyPair.getPublic())
//                .privateKey((RSAPrivateKey) keyPair.getPrivate())
//                .keyID(UUID.randomUUID().toString())
//                .build();
//    }
//
//    private KeyPair generateRsaKey() {
//        try {
//            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
//            keyPairGenerator.initialize(2048);
//            return keyPairGenerator.generateKeyPair();
//        } catch (NoSuchAlgorithmException e) {
//            throw new IllegalStateException("RSA Key Pair generation failed", e);
//        }
//    }
//
//    // Token generation method (optional for a resource server, more useful for an auth server)
//    public String generateToken(String username) {
//        JwtClaimsSet claims = JwtClaimsSet.builder()
//                .subject(username)
//                .claim("roles", List.of("USER"))
//                .issuedAt(Instant.now())
//                .expiresAt(Instant.now().plus(1, ChronoUnit.HOURS))
//                .build();
//
//        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
//    }ername) {
//        JwtClaimsSet claims = JwtClaimsSet.builder()
//                .subject(username)
//                .claim("roles", List.of("USER"))
//                .issuedAt(Instant.now())
//                .expiresAt(Instant.now().plus(1, ChronoUnit.HOURS))
//                .build();
//
//        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
//    }
}
