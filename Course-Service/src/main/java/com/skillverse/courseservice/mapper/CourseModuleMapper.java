package com.skillverse.courseservice.mapper;

import com.skillverse.courseservice.dto.request.CourseModuleRequestDTO;
import com.skillverse.courseservice.dto.response.CourseModuleResponseDTO;
import com.skillverse.courseservice.model.CourseModule;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CourseModuleMapper {

    @Mapping(target = "courseId", source = "course.courseId")
    CourseModuleResponseDTO toDTO(CourseModule entity);

    @Mapping(target = "moduleId", ignore = true)
    @Mapping(target = "course", ignore = true)
    CourseModule toEntity(CourseModuleRequestDTO dto);

    @BeanMapping(
            nullValuePropertyMappingStrategy =
                    NullValuePropertyMappingStrategy.IGNORE
    )
    @Mapping(target = "moduleId", ignore = true)
    @Mapping(target = "course", ignore = true)
    void updateEntityFromDto(
            CourseModuleRequestDTO dto,
            @MappingTarget CourseModule entity
    );
}
