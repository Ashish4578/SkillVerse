package com.skillverse.courseservice.controller;

import com.skillverse.courseservice.DTO.CourseDetailsRequestDTO;
import com.skillverse.courseservice.DTO.CourseDetailsResponseDTO;
import com.skillverse.courseservice.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skillverse/courses")
public class CoursesController {

    private final CourseService courseService;

    public CoursesController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
	public String msg() {
		return "Hello World from Courses Service";
	}

    @GetMapping("/details/{courseId}")
    public String getCourseDetails() {
        return "Course details for course id: ";
    }

    @GetMapping("/all")
    public ResponseEntity<List<CourseDetailsResponseDTO>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @PostMapping("/addCourse")
    public ResponseEntity<CourseDetailsResponseDTO> addCourse(@RequestBody CourseDetailsRequestDTO courseDetailsRequestDTO) {
        return ResponseEntity.ok(courseService.addCourse(courseDetailsRequestDTO));
    }

    @PutMapping("/updateCourse/{courseId}")
    public ResponseEntity<CourseDetailsResponseDTO> updateCourse(@RequestBody CourseDetailsRequestDTO courseDetailsRequestDTO, @PathVariable Long courseId) {
        return ResponseEntity.ok(courseService.updateCourse(courseId, courseDetailsRequestDTO));
    }
    @DeleteMapping("/deleteCourse/{courseId}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long courseId) {
        courseService.deleteCourse(courseId);
        return new ResponseEntity<>("Course Deleted Successfully", HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<CourseDetailsResponseDTO>> searchCourses(@RequestParam String keyword) {
        return ResponseEntity.ok(courseService.searchCourse(keyword));
    }

}
