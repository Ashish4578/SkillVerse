package com.skillverse.enroll.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "enrollments",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_user_course", columnNames = {"user_id", "course_id"})
        },
        indexes = {
                @Index(name = "idx_user_id", columnList = "user_id"),
                @Index(name = "idx_course_id", columnList = "course_id")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔹 User reference (from User Service)
    @Column(name = "user_id", nullable = false)
    private Long userId;

    // 🔹 Course reference (from Course Service)
    @Column(name = "course_id", nullable = false)
    private Long courseId;

    // 🔹 Status (future ready)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EnrollmentStatus status;

    // 🔹 Audit fields
    @Column(updatable = false)
    private LocalDateTime enrolledAt;

    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        this.enrolledAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();

        if (this.status == null) {
            this.status = EnrollmentStatus.ACTIVE;
        }
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}