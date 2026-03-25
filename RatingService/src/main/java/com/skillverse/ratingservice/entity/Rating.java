package com.skillverse.ratingservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "rating")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Rating {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ratingId;

    @NotNull
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<CourseDetails> courseDetails=new HashSet<>();

    private Long createrId;

    private Long courseId;

	@Size(min = 1, message = "User must have at least one role")
	@Enumerated(EnumType.STRING)
	@NotNull
	@ElementCollection(fetch = FetchType.EAGER)
	private Set<RatingsRate> rate=new HashSet<>();
	
	
}
