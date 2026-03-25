package com.skillverse.ratingservice.repo;

import com.skillverse.ratingservice.dto.CreaterResponseDT0;
import com.skillverse.ratingservice.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RatingsServiceRepository extends JpaRepository<Rating, Long> {

//    @Query("SELECT u.rating FROM Rating u WHERE u.creatorId = :creatorId")
//    List<CreaterResponseDT0> findAllRatingsOfCreator(String creatorId);
}
