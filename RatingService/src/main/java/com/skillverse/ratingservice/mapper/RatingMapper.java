package com.skillverse.ratingservice.mapper;

import com.skillverse.ratingservice.dto.request.RatingRequestDTO;
import com.skillverse.ratingservice.dto.response.RatingResponseDTO;
import com.skillverse.ratingservice.entity.Rating;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface RatingMapper {

    // Entity → DTO
    RatingResponseDTO toDTO(Rating entity);

    // DTO → Entity
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Rating toEntity(RatingRequestDTO dto);

    // Update existing entity
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    void updateEntityFromDto(RatingRequestDTO dto,
                             @MappingTarget Rating entity);
}