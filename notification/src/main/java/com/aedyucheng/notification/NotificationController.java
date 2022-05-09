package com.aedyucheng.notification;

import com.aedyucheng.clients.notification.NotificationRequest;
import com.aedyucheng.clients.notification.NotificationResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1/notification")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping
    public NotificationResponse sendNotification(@RequestBody NotificationRequest notificationRequest){
        log.info("New notification request {}", notificationRequest);
        notificationService.sendNotification(notificationRequest);
        return new NotificationResponse();
    }
}
