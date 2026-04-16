package com.skillverse.notificationservice.service;

import com.skillverse.notificationservice.dto.response.NotificationResponseDTO;
import com.skillverse.notificationservice.entity.Notification;
import com.skillverse.notificationservice.mapper.NotificationMapper;
import com.skillverse.notificationservice.repo.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository repository;
    private final NotificationMapper mapper;

    public void saveNotification(Long userId, Long courseId) {

        Notification notification = Notification.builder()
                .userId(userId)
                .title("Course Enrollment 🎉")
                .message("You enrolled in course ID: " + courseId)
                .build();

        repository.save(notification);
    }

    public Page<NotificationResponseDTO> getMyNotifications(Long userId, Pageable pageable) {
        return repository.findByUserId(userId, pageable)
                .map(mapper::toDTO);
    }

    public long getUnreadCount(Long userId) {
        return repository.countByUserIdAndIsReadFalse(userId);
    }

    public void markAsRead(Long notificationId) {
        Notification n = repository.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notification not found"));

        n.setRead(true);
        repository.save(n);
    }
}