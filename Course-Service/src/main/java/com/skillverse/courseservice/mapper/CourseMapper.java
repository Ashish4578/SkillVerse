package com.skillverse.courseservice.mapper;

import com.skillverse.courseservice.DTO.request.CourseDetailsRequestDTO;
import com.skillverse.courseservice.DTO.response.CourseDetailsResponseDTO;
import com.skillverse.courseservice.model.CourseDetails;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    CourseDetailsResponseDTO toDTO(CourseDetails entity);

    @Mapping(target = "courseId", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    CourseDetails toEntity(CourseDetailsRequestDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "courseId", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    void updateEntityFromDto(CourseDetailsRequestDTO dto,
                             @MappingTarget CourseDetails entity);
}