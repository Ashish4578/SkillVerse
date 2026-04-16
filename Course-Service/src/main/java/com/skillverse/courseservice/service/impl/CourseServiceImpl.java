package com.skillverse.courseservice.service.impl;

import com.skillverse.courseservice.DTO.request.CourseDetailsRequestDTO;
import com.skillverse.courseservice.DTO.response.CourseDetailsResponseDTO;
import com.skillverse.courseservice.execption.ResourceNotFoundException;
import com.skillverse.courseservice.execption.UnauthorizedException;
import com.skillverse.courseservice.mapper.CourseMapper;
import com.skillverse.courseservice.model.CourseDetails;
import com.skillverse.courseservice.model.UserRequestContext;
import com.skillverse.courseservice.repository.CourseRepository;
import com.skillverse.courseservice.service.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    private static final String ROLE_CREATOR = "ROLE_CREATOR";

    @Transactional(readOnly = true)
    @Override
    public CourseDetailsResponseDTO getCourseById(Long courseId) {
        return courseMapper.toDTO(getCourseOrThrow(courseId));
    }

    @Transactional(readOnly = true)
    @Override
    public Page<CourseDetailsResponseDTO> getAllCourses(Pageable pageable) {

        return courseRepository.findAll(pageable)
                .map(courseMapper::toDTO);
    }

    @Transactional
    @Override
    public CourseDetailsResponseDTO createCourse(UserRequestContext context,
                                                 CourseDetailsRequestDTO request) {

        validateCreator(context);

        log.info("Creating course for userId={}", context.getUserId());

        sanitize(request);

        CourseDetails course = courseMapper.toEntity(request);
        course.setCreatedBy(context.getUserId());

        return courseMapper.toDTO(courseRepository.save(course));
    }

    @Transactional
    @Override
    public CourseDetailsResponseDTO updateCourse(UserRequestContext context,
                                                 Long courseId,
                                                 CourseDetailsRequestDTO request) {

        validateCreator(context);

        CourseDetails course = getCourseOrThrow(courseId);

        validateOwnership(context, course);

        sanitize(request);

        courseMapper.updateEntityFromDto(request, course);

        return courseMapper.toDTO(courseRepository.save(course));
    }
    @Override
    @Transactional
    public void updateCourseRating(Long courseId, int newRating) {

        CourseDetails course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        double currentAvg = course.getAverageRating();
        long total = course.getTotalRatings();

        double newAvg = ((currentAvg * total) + newRating) / (total + 1);

        course.setAverageRating(newAvg);
        course.setTotalRatings(total + 1);

        courseRepository.save(course);
    }

    @Transactional
    @Override
    public void deleteCourse(UserRequestContext context, Long courseId) {

        validateCreator(context);

        CourseDetails course = getCourseOrThrow(courseId);

        validateOwnership(context, course);

        courseRepository.delete(course);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<CourseDetailsResponseDTO> searchCourse(String keyword, Pageable pageable) {

        return courseRepository
                .findByCourseNameContainingIgnoreCaseOrCourseDescriptionContainingIgnoreCase(keyword, keyword, pageable)
                .map(courseMapper::toDTO);
    }

    // ================== HELPERS ==================

    private CourseDetails getCourseOrThrow(Long courseId) {
        return courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));
    }

    private void validateCreator(UserRequestContext context) {
        if (!ROLE_CREATOR.equals(context.getRole())) {
            throw new UnauthorizedException("Only creators can perform this action");
        }
    }

    private void validateOwnership(UserRequestContext context, CourseDetails course) {
        if (!course.getCreatedBy().equals(context.getUserId())) {
            throw new UnauthorizedException("You are not owner of this course");
        }
    }

    private void sanitize(CourseDetailsRequestDTO request) {
        request.setCourseName(request.getCourseName().trim());
        request.setCourseDescription(request.getCourseDescription().trim());
    }
}