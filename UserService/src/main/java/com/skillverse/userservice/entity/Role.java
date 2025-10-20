package com.skillverse.userservice.entity;

import java.util.Set;

public enum Role {
	ROLE_STUDENT(Set.of(
			Permission.READ_COURSE
			)),
	ROLE_CREATOR(Set.of(
			Permission.READ_COURSE,
			Permission.CREATE_COURSE)),
	ROLE_ADMIN(
			Set.of(
			Permission.READ_COURSE,
			Permission.CREATE_COURSE,
			Permission.DELETE_COURSE,
			Permission.DELETE_USER)),
	ROLE_SUPER_ADMIN(Set.of(
			Permission.values()
			)); // All permissions

	private final Set<Permission> permissions;

	Role(Set<Permission> permissions) {
		this.permissions = permissions;
	}

	public Set<Permission> getPermissions() {
		return permissions;
	}
}
