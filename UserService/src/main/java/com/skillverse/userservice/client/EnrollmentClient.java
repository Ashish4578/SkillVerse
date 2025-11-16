package com.skillverse.userservice.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CircuitBreaker(name = "enrollmentServiceCB", fallbackMethod = "enrollmentFallback")
public class EnrollmentClient {

}
