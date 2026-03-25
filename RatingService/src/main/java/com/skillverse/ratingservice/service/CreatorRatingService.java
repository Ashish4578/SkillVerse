package com.skillverse.ratingservice.service;

import com.skillverse.ratingservice.dto.RatingsServiceRequestDTO;
import com.skillverse.ratingservice.dto.RatingsServiceResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CreatorRatingService {

    RatingsServiceResponseDTO giveRatingToCreator(RatingsServiceRequestDTO ratingsServiceRequestDTO);

    List<RatingsServiceResponseDTO> getRatingOfCreator(String creatorId);

    RatingsServiceResponseDTO getRatingOfCourse(String courseId);

    List<RatingsServiceResponseDTO> getAllRatingsOfCreator();

}
