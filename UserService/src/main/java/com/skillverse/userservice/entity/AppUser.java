package com.skillverse.userservice.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.EnumType;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AppUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long appUserId;
	
	@Column
	@NotNull
	private String username;

	@Column
	@NotNull
	private String password;

	@Column
	private boolean enabled;

	@Column
	@NotNull
	private String email;
	
	@Column
	@NotNull
	private String contactNumber;

	@ElementCollection(fetch = FetchType.EAGER)
	@Enumerated(EnumType.STRING)
	@NotNull
	private Set<Role> roles = new HashSet<>();

}
