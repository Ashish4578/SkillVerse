package com.skillverse.ratingservice.dto;

import com.skillverse.ratingservice.entity.CourseDetails;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class CreaterResponseDT0 {
    private String createrId;

    private String createrName;

    List<CourseDetails> courseDetails;
}
