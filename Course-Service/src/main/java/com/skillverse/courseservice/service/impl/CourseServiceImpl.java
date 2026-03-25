package com.skillverse.courseservice.service.impl;

import com.skillverse.courseservice.DTO.CourseDetailsRequestDTO;
import com.skillverse.courseservice.DTO.CourseDetailsResponseDTO;
import com.skillverse.courseservice.model.CourseDetails;
import com.skillverse.courseservice.repository.CourseServiceRepository;
import com.skillverse.courseservice.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.skillverse.courseservice.mapper.CourseServiceMapper.getConvertCourseDetailsToCourseDetailsResponseDTO;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseServiceRepository courseServiceRepository;

    @Override
    public CourseDetailsResponseDTO getCourseDetails(Long courseId) {
        CourseDetails courseDetails = courseServiceRepository.findById(courseId).get();
        return getConvertCourseDetailsToCourseDetailsResponseDTO(courseDetails);
    }

    @Override
    public List<CourseDetailsResponseDTO> getAllCourses() {

        List<CourseDetails> courseDetailsList = courseServiceRepository.findAll();
        List<CourseDetailsResponseDTO> courseDetailsResponseDTOS = new ArrayList<>();
        for (CourseDetails courseDetails : courseDetailsList) {
            courseDetailsResponseDTOS.add(getConvertCourseDetailsToCourseDetailsResponseDTO(courseDetails));
        }
        return courseDetailsResponseDTOS;
    }

    @Override
    public CourseDetailsResponseDTO addCourse(CourseDetailsRequestDTO courseDetailsRequestDTO) {
        CourseDetails courseDetails = new CourseDetails();
        courseDetails.setCourseName(courseDetailsRequestDTO.getCourseName());
        courseDetails.setCourseDescription(courseDetailsRequestDTO.getCourseDescription());
        courseDetails.setCourseInstructor(courseDetailsRequestDTO.getCourseInstructor());
        courseDetails.setCoursePrice(courseDetailsRequestDTO.getCoursePrice());
        courseDetails.setCourseDuration(courseDetailsRequestDTO.getCourseDuration());
        courseDetails.setCourseTime(courseDetailsRequestDTO.getCourseTime());

        return getConvertCourseDetailsToCourseDetailsResponseDTO(courseServiceRepository.save(courseDetails));
    }

    @Override
    public CourseDetailsResponseDTO updateCourse(Long courseId, CourseDetailsRequestDTO courseDetailsRequestDTO) {
        CourseDetails courseDetails = courseServiceRepository.findById(courseId).get();
        courseDetails.setCourseName(courseDetailsRequestDTO.getCourseName());
        courseDetails.setCourseDescription(courseDetailsRequestDTO.getCourseDescription());
        courseDetails.setCourseInstructor(courseDetailsRequestDTO.getCourseInstructor());
        courseDetails.setCoursePrice(courseDetailsRequestDTO.getCoursePrice());
        courseDetails.setCourseDuration(courseDetailsRequestDTO.getCourseDuration());
        courseDetails.setCourseTime(courseDetailsRequestDTO.getCourseTime());

        return getConvertCourseDetailsToCourseDetailsResponseDTO(courseServiceRepository.save(courseDetails));
    }

    @Override
    public void deleteCourse(Long courseId) {
        Optional<CourseDetails> courseDetailsOptional = Optional.ofNullable(courseServiceRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + courseId)));
        courseServiceRepository.deleteById(courseId);
    }

    @Override
    public List<CourseDetailsResponseDTO> searchCourse(String keyword) {
        return List.of();
    }
}
