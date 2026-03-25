package com.skillverse.ratingservice.controller;

import com.skillverse.ratingservice.dto.RatingsServiceRequestDTO;
import com.skillverse.ratingservice.service.CreatorRatingService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("skillverse/ratings")
public class RatingServiceController {
    private CreatorRatingService creatorRatingService;

	@GetMapping
	public String msg() {
		return "Hello World from Ratings Service";
	}

    @GetMapping("getRating/{courseId}")
    public String getRatingsByCourseId(@PathVariable String courseId) {
        return "Ratings for course with ID: " + courseId;
    }
    @GetMapping("getAllRatings/{createrId}")
    public String getAllRatingsByCreaterId(@PathVariable String createrId) {
        return "All ratings for creator with ID: " + createrId;
    }
    @GetMapping("getAllCourseRatings")
    public String getAllCourseRatings() {
        return "All course ratings";
    }
    @PostMapping("addRating")
    public String addRating(@RequestParam RatingsServiceRequestDTO ratingsServiceRequestDTO) {
        return "d";
    }
}
