package com.skillverse.authentication.server.client;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.skillverse.authentication.server.dto.UserResponse;

import reactor.core.publisher.Mono;

@Component
public class UserServiceClient {

	private final WebClient webClient;

	public UserServiceClient(WebClient.Builder builder) {
		this.webClient = builder.baseUrl("http://localhost:8081").build(); // user-service URL
	}

	public Mono<UserResponse> findUserByUsername(String username) {
		return webClient.get().uri("/users/{username}", username).retrieve()
				.onStatus(status -> status.value() >= 400 && status.value() < 500, response -> Mono.empty())
				.bodyToMono(UserResponse.class);
	}

	public Mono<UserResponse> registerUser(String username) {
		return webClient.post().uri("/users/register").bodyValue(Map.of("username", username)) // Or custom DTO
				.retrieve().bodyToMono(UserResponse.class);
	}
}
