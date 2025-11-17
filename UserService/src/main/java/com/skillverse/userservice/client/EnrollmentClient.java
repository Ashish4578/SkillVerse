package com.skillverse.userservice.client;

import com.skillverse.userservice.dto.AppUserResponseDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Slf4j
@CircuitBreaker(name = "enrollmentServiceCB", fallbackMethod = "enrollmentFallback")
public class EnrollmentClient {

    @Autowired
    private WebClient webClient;

    @Bean
    public WebClient webClientEnrollment(WebClient.Builder builder) {
        return builder
                .baseUrl("http://ENROLLMENT-SERVICE/enrollment")
                .build();
    }


    public Map<AppUserResponseDTO, List<AppUserResponseDTO>> getAllCreatorsWithSubscribeUsers() {
        webClient.
                get()
                .uri("/enrollment/creators-with-subscribers")
                .retrieve()
                .bodyToMono(AppUserResponseDTO.class)
                .block();
        return null;
    }
}
