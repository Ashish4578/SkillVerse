package com.skillverse.courseservice.service;

import com.skillverse.courseservice.DTO.request.CourseDetailsRequestDTO;
import com.skillverse.courseservice.DTO.response.CourseDetailsResponseDTO;
import com.skillverse.courseservice.model.UserRequestContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CourseService {

    CourseDetailsResponseDTO getCourseById(Long courseId);

    Page<CourseDetailsResponseDTO> getAllCourses(Pageable pageable);

    CourseDetailsResponseDTO createCourse(UserRequestContext context, CourseDetailsRequestDTO request);

    CourseDetailsResponseDTO updateCourse(UserRequestContext context, Long courseId, CourseDetailsRequestDTO request);

    void deleteCourse(UserRequestContext context, Long courseId);

    Page<CourseDetailsResponseDTO> searchCourse(String keyword, Pageable pageable);

    public void updateCourseRating(Long courseId, int newRating);
}