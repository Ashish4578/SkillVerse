package com.skillverse.courseservice.mapper;

import com.skillverse.courseservice.DTO.request.LessonRequestDTO;
import com.skillverse.courseservice.DTO.response.LessonResponseDTO;
import com.skillverse.courseservice.model.Lesson;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface LessonMapper {

    @Mapping(target = "moduleId",
            source = "module.moduleId")
    LessonResponseDTO toDTO(Lesson entity);

    @Mapping(target = "lessonId",
            ignore = true)
    @Mapping(target = "module",
            ignore = true)
    Lesson toEntity(LessonRequestDTO dto);

    @BeanMapping(
            nullValuePropertyMappingStrategy =
                    NullValuePropertyMappingStrategy.IGNORE
    )
    @Mapping(target = "lessonId",
            ignore = true)
    @Mapping(target = "module",
            ignore = true)
    void updateEntityFromDto(
            LessonRequestDTO dto,
            @MappingTarget Lesson entity
    );
}
