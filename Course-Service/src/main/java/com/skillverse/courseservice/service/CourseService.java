package com.skillverse.courseservice.service;

import com.skillverse.courseservice.DTO.CourseDetailsRequestDTO;
import com.skillverse.courseservice.DTO.CourseDetailsResponseDTO;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface CourseService {

    CourseDetailsResponseDTO getCourseDetails(Long courseId);

    List<CourseDetailsResponseDTO> getAllCourses();

    CourseDetailsResponseDTO addCourse(CourseDetailsRequestDTO courseDetailsRequestDTO);

    CourseDetailsResponseDTO updateCourse(Long courseId, CourseDetailsRequestDTO courseDetailsRequestDTO);

    void deleteCourse(Long courseId);

    List<CourseDetailsResponseDTO> searchCourse(@RequestParam String keyword);

}
