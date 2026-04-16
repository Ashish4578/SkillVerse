package com.skillverse.enroll.service.impl;

import com.skillverse.enroll.client.CourseServiceClient;
import com.skillverse.enroll.client.UserServiceClient;
import com.skillverse.enroll.config.EnrollmentEventProducer;
import com.skillverse.enroll.dto.request.EnrollmentRequestDTO;
import com.skillverse.enroll.dto.response.EnrollmentResponseDTO;
import com.skillverse.enroll.exception.DuplicateUserException;
import com.skillverse.enroll.exception.ResourceNotFoundException;
import com.skillverse.enroll.exception.UnauthorizedException;
import com.skillverse.enroll.mapper.EnrollmentMapper;
import com.skillverse.enroll.model.Enrollment;
import com.skillverse.enroll.model.EnrollmentEvent;
import com.skillverse.enroll.model.UserRequestContext;
import com.skillverse.enroll.repo.EnrollmentRepository;
import com.skillverse.enroll.service.EnrollmentService;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final EnrollmentMapper enrollmentMapper;
    private final UserServiceClient userClient;
    private final CourseServiceClient courseClient;
    private final EnrollmentEventProducer producer;

    //  Enroll in course
    @Override
    public EnrollmentResponseDTO enroll(UserRequestContext context,
                                        EnrollmentRequestDTO request) {

        validateStudent(context);

        Long userId = context.getUserId();
        Long courseId = request.getCourseId();

        log.info("Enrollment attempt userId={} courseId={}", userId, courseId);

        validateUser(userId);
        validateCourse(courseId);
        validateDuplicate(userId, courseId);

        Enrollment enrollment = enrollmentMapper.toEntity(request);
        enrollment.setUserId(userId);

        Enrollment saved = enrollmentRepository.save(enrollment);

        // Kafka event
        EnrollmentEvent event = EnrollmentEvent.builder()
                .userId(saved.getUserId())
                .courseId(saved.getCourseId())
                .status(saved.getStatus().name())
                .build();

        try {
            producer.publishEnrollmentEvent(event);
        } catch (Exception ex) {
            log.error("Kafka publish failed for enrollmentId={}", saved.getId(), ex);
        }

        return enrollmentMapper.toDTO(saved);
    }

    //  Get my enrollments
    @Override
    public Page<EnrollmentResponseDTO> getMyEnrollments(UserRequestContext context,
                                                        Pageable pageable) {

        Long userId = context.getUserId();

        return enrollmentRepository.findByUserId(userId, pageable)
                .map(enrollmentMapper::toDTO);
    }

    //  Get enrollments by course (for creator/admin later)
    @Override
    public Page<EnrollmentResponseDTO> getEnrollmentsByCourse(Long courseId,
                                                              Pageable pageable) {

        return enrollmentRepository.findByCourseId(courseId, pageable)
                .map(enrollmentMapper::toDTO);
    }

    // ================= HELPER METHODS =================

    private void validateStudent(UserRequestContext context) {
        if (!"ROLE_STUDENT".equals(context.getRole())) {
            throw new UnauthorizedException("Only students can enroll in courses");
        }
    }
    private void validateUser(Long userId) {
        try {
            userClient.getUserById(userId, "internal");
        } catch (FeignException.NotFound ex) {
            throw new ResourceNotFoundException("User not found");
        } catch (Exception ex) {
            log.error("User service error", ex);
            throw new RuntimeException("User service unavailable");
        }
    }
    private void validateCourse(Long courseId) {
        try {
            courseClient.getCourseById(courseId, "internal");
        } catch (FeignException.NotFound ex) {
            throw new ResourceNotFoundException("Course not found");
        } catch (Exception ex) {
            log.error("Course service error", ex);
            throw new RuntimeException("Course service unavailable");
        }
    }
    private void validateDuplicate(Long userId, Long courseId) {
        if (enrollmentRepository.existsByUserIdAndCourseId(userId, courseId)) {
            throw new DuplicateUserException("Already enrolled");
        }
    }
}