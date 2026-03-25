package com.skillverse.ratingservice.service.impl;

import com.skillverse.ratingservice.dto.RatingsServiceRequestDTO;
import com.skillverse.ratingservice.dto.RatingsServiceResponseDTO;
import com.skillverse.ratingservice.entity.Rating;
import com.skillverse.ratingservice.repo.RatingsServiceRepository;
import com.skillverse.ratingservice.service.CreatorRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import com.skillverse.ratingservice.mapper.RatingsServiceMapper.*;
import java.util.List;

import static com.skillverse.ratingservice.mapper.RatingsServiceMapper.getConvertRatingServiceRequestDTOToRating;
import static com.skillverse.ratingservice.mapper.RatingsServiceMapper.getConvertRatingToRatingServiceResponseDTO;

public class CreatorRatingServiceImpl implements CreatorRatingService {

    @Autowired
    private RatingsServiceRepository ratingsServiceRepository;

    @Override
    public RatingsServiceResponseDTO giveRatingToCreator(RatingsServiceRequestDTO ratingsServiceRequestDTO) {
        return getConvertRatingToRatingServiceResponseDTO(ratingsServiceRepository.save(getConvertRatingServiceRequestDTOToRating( ratingsServiceRequestDTO)));
    }

    @Override
    public List<RatingsServiceResponseDTO> getRatingOfCreator(String creatorId) {

       return null;
    }

    @Override
    public RatingsServiceResponseDTO getRatingOfCourse(String courseId) {
        return null;
    }

    @Override
    public List<RatingsServiceResponseDTO> getAllRatingsOfCreator() {
        return List.of();
    }
}
