package com.skillverse.enroll.mapper;

import com.skillverse.enroll.dto.request.EnrollmentRequestDTO;
import com.skillverse.enroll.dto.response.EnrollmentResponseDTO;
import com.skillverse.enroll.model.Enrollment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EnrollmentMapper {

    // Entity → Response DTO
    @Mapping(target = "status", source = "status")
    EnrollmentResponseDTO toDTO(Enrollment enrollment);

    // Request → Entity
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userId", ignore = true) // set from context
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "enrolledAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Enrollment toEntity(EnrollmentRequestDTO dto);
}