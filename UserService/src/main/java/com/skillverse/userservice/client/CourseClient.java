package com.skillverse.userservice.client;

import com.skillverse.userservice.entity.Course;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@CircuitBreaker(name = "createrProfileService", fallbackMethod = "getCourseListFallback")
public class CourseClient {

    @Autowired
    private WebClient webClient;

    public Course[] getCourseList() {
        // TODO Auto-generated method stub
       return webClient
               .get()
               .uri("/courses").
               retrieve()
               .bodyToMono(Course[].class)
               .block();
    }

    public Course[] getCourseListFallback(Throwable t) {
        // Provide fallback response, e.g. empty array or cached list
        return new Course[0];
    }
}
