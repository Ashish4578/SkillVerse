package com.skillverse.authentication.server.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserCreatedDomainEvent {
    private User user;
}