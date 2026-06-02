package com.skillverse.notificationservice.model;

import lombok.Data;

@Data
public class UserInfo {
    private Long id;
    private String username;
    private String email;
    private String contactNumber;

}