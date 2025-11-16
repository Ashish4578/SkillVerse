package com.skillverse.userservice.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CircuitBreaker(name = "notificationServiceCB", fallbackMethod = "notificationFallback")
public class NotificationClient {

}
