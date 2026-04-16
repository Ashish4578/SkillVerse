package com.skillverse.courseservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "courses", indexes = {
        @Index(name = "idx_created_by", columnList = "createdBy"),
        @Index(name = "idx_course_name", columnList = "courseName")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;

    @Column(nullable = false)
    private String courseName;

    @Column(nullable = false, length = 500)
    private String courseDescription;

    @Column(nullable = false)
    private String courseInstructor;

    @Column(nullable = false)
    private String courseTime;

    @Column(nullable = false)
    private Long createdBy;

    @Column(nullable = false)
    private double coursePrice;

    private String courseDuration;

    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Version
    private Long version;
    private double averageRating;
    private long totalRatings;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
