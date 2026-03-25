package com.skillverse.authentication.server.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "skillverse_auth_roles")
public class ROLE {
    //    USER, CREATOR, ADMIN, SUPER_ADMIN
    @Id
    private UUID id = UUID.randomUUID();
    private String name;
}
