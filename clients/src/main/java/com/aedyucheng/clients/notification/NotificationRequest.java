package com.aedyucheng.clients.notification;

import lombok.Builder;

@Builder
public record NotificationRequest(
        String message,
        Integer toCustomerId,
        String toCustomerEmail
) {


}
