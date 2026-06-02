package com.skillverse.enroll.client;

import com.skillverse.enroll.model.CourseInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "COURSE-SERVICE")
public interface CourseServiceClient {

    @GetMapping("/courses/internal/{courseId}")
    CourseInfo getCourseById(@PathVariable Long courseId,
                             @RequestHeader("X-Internal-Call") String internal);
}