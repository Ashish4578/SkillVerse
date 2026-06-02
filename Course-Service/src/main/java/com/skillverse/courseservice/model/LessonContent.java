package com.skillverse.courseservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "lesson_contents")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LessonContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contentId;

    @Enumerated(EnumType.STRING)
    private ContentType contentType;

    @Column(length = 1000)
    private String contentUrl;

    @Column(columnDefinition = "TEXT")
    private String textContent;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "lesson_id", nullable = false)
    private Lesson lesson;
}