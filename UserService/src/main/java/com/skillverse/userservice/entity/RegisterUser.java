package com.skillverse.userservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUser {
    private String username;
    private String password;
    private String confirmPassword;
    private String email;
    private String phone;
    private String address;
    private TypesOfUser role;
}
