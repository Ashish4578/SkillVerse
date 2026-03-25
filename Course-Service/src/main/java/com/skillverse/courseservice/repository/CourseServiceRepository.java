package com.skillverse.courseservice.repository;

import com.skillverse.courseservice.model.CourseDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseServiceRepository extends JpaRepository<CourseDetails, Long> {
}
