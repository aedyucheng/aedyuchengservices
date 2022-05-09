package com.aedyucheng.notification;

import com.aedyucheng.clients.notification.NotificationRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public record NotificationService(NotificationRepository notificationRepository) {

    public void sendNotification(NotificationRequest notificationRequest) {
        notificationRepository.save(
                Notification.builder()
                        .message(notificationRequest.message())
                        .sender("Aedyucheng.com")
                        .toCustomerId(notificationRequest.toCustomerId())
                        .toCustomerEmail(notificationRequest.toCustomerEmail())
                        .sentAt(LocalDateTime.now())
                        .build()
        );

    }
}
