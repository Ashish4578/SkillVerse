package com.skillverse.courseservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "course_progress", uniqueConstraints = { @UniqueConstraint(columnNames = {"userId", "lessonId"})})
public class CourseProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long progressId;

    private Long userId;

    private Long courseId;

    private Long lessonId;

    private boolean completed;

    private LocalDateTime completedAt;
}