package com.skillverse.courseservice.mapper;

import com.skillverse.courseservice.DTO.request.LessonContentRequestDTO;
import com.skillverse.courseservice.DTO.response.LessonContentResponseDTO;
import com.skillverse.courseservice.model.LessonContent;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface LessonContentMapper {

    @Mapping(target = "lessonId",
            source = "lesson.lessonId")
    LessonContentResponseDTO toDTO(
            LessonContent entity);

    @Mapping(target = "contentId",
            ignore = true)
    @Mapping(target = "lesson",
            ignore = true)
    LessonContent toEntity(
            LessonContentRequestDTO dto);

    @BeanMapping(
            nullValuePropertyMappingStrategy =
                    NullValuePropertyMappingStrategy.IGNORE
    )
    @Mapping(target = "contentId",
            ignore = true)
    @Mapping(target = "lesson",
            ignore = true)
    void updateEntityFromDto(
            LessonContentRequestDTO dto,
            @MappingTarget LessonContent entity);
}