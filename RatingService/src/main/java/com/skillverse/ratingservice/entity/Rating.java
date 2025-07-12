package com.skillverse.ratingservice.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table
@Data
public class Rating {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long appUserId;

	@Size(min = 1, message = "User must have at least one role")
	@Enumerated(EnumType.STRING)
	@NotNull
	@ElementCollection(fetch = FetchType.EAGER)
	
	private Set<RatingsRate> rate=new HashSet<>();
	
	
}
