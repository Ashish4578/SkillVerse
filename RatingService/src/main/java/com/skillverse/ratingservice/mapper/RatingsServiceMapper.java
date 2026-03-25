package com.skillverse.ratingservice.mapper;

import com.skillverse.ratingservice.dto.RatingsServiceRequestDTO;
import com.skillverse.ratingservice.dto.RatingsServiceResponseDTO;
import com.skillverse.ratingservice.entity.Rating;

public class RatingsServiceMapper {

    public static Rating getConvertRatingServiceRequestDTOToRating(RatingsServiceRequestDTO requestDTO) {
        return Rating.builder()
                .createrId(requestDTO.getCreatorId())
                .courseId(requestDTO.getCourseId())
                .rate(requestDTO.getRating().getRate())
                .build();
    }

    public static RatingsServiceResponseDTO getConvertRatingToRatingServiceResponseDTO(Rating rating) {
        return RatingsServiceResponseDTO.builder()
                .creatorId(rating.getCreaterId())
                .courseId(rating.getCourseId())
                .rating(rating)
                .build();
    }

}
