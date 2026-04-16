package com.skillverse.notificationservice.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class NotificationResponseDTO {

    private Long id;
    private String title;
    private String message;
    private boolean isRead;
    private LocalDateTime createdAt;

}