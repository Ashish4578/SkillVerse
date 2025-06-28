package com.skillverse.userservice.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "app_user")
@Data
@NoArgsConstructor
public class AppUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long appUserId;

	@NotBlank(message = "Username cannot be empty")
	@Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
	private String username;

	@NotBlank(message = "Password cannot be empty")
	@Size(min = 8, message = "Password must be at least 8 characters long")
	// Consider adding more complex password validation (e.g., regex for special
	// chars, numbers)
	private String password;

	@NotBlank(message = "Email cannot be empty")
	@Email(message = "Invalid email format")
	@Size(max = 100, message = "Email cannot exceed 100 characters")
	private String email;

	@NotBlank(message = "Contact number cannot be empty")
	@Pattern(regexp = "\\d{10}", message = "Contact number must be 10 digits")
	private String contactNumber;

	private boolean enabled = true;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "app_user_id"))
	@Column(name = "role_name")
	@Enumerated(EnumType.STRING)
	@NotNull
	@Size(min = 1, message = "User must have at least one role")
	private Set<Role> roles = new HashSet<>();

	public AppUser(
			@NotBlank(message = "Username cannot be empty") @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters") String username,
			@NotBlank(message = "Password cannot be empty") @Size(min = 8, message = "Password must be at least 8 characters long") String password,
			@NotBlank(message = "Email cannot be empty") @Email(message = "Invalid email format") @Size(max = 100, message = "Email cannot exceed 100 characters") String email,
			@NotBlank(message = "Contact number cannot be empty") @Pattern(regexp = "\\d{10}", message = "Contact number must be 10 digits") String contactNumber,
			boolean enabled, @NotNull @Size(min = 1, message = "User must have at least one role") Set<Role> roles) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.contactNumber = contactNumber;
		this.enabled = enabled;
		this.roles = roles;
	}

}
