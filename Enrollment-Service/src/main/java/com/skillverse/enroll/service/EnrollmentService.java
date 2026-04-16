package com.skillverse.enroll.service;

import com.skillverse.enroll.dto.request.EnrollmentRequestDTO;
import com.skillverse.enroll.dto.response.EnrollmentResponseDTO;
import com.skillverse.enroll.model.UserRequestContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EnrollmentService {

    EnrollmentResponseDTO enroll(UserRequestContext context, EnrollmentRequestDTO request);

    Page<EnrollmentResponseDTO> getMyEnrollments(UserRequestContext context, Pageable pageable);

    Page<EnrollmentResponseDTO> getEnrollmentsByCourse(Long courseId, Pageable pageable);
}