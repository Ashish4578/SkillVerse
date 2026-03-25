package com.skillverse.courseservice.mapper;

import com.skillverse.courseservice.DTO.CourseDetailsRequestDTO;
import com.skillverse.courseservice.DTO.CourseDetailsResponseDTO;
import com.skillverse.courseservice.model.CourseDetails;

public class CourseServiceMapper {

    public static CourseDetailsResponseDTO getConvertCourseDetailsToCourseDetailsResponseDTO(CourseDetails courseDetails) {
        CourseDetailsResponseDTO responseDTO = new CourseDetailsResponseDTO();
        responseDTO.setCourseName(courseDetails.getCourseName());
        responseDTO.setCourseDescription(courseDetails.getCourseDescription());
        responseDTO.setCourseInstructor(courseDetails.getCourseInstructor());
        responseDTO.setCoursePrice(courseDetails.getCoursePrice());
        responseDTO.setCourseDuration(courseDetails.getCourseDuration());
        responseDTO.setCourseTime(courseDetails.getCourseTime());
        return responseDTO;
    }
    public static CourseDetailsRequestDTO getConvertCourseDetailsToCourseDetailsRequestDTO(CourseDetailsRequestDTO courseDetails) {
        CourseDetailsRequestDTO requestDTO = new CourseDetailsRequestDTO();
        requestDTO.setCourseName(courseDetails.getCourseName());
        requestDTO.setCourseDescription(courseDetails.getCourseDescription());
        requestDTO.setCourseInstructor(courseDetails.getCourseInstructor());
        requestDTO.setCoursePrice(courseDetails.getCoursePrice());
        requestDTO.setCourseDuration(courseDetails.getCourseDuration());
        requestDTO.setCourseTime(courseDetails.getCourseTime());
        return requestDTO;
    }
}
