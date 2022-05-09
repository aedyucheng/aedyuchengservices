package com.aedyucheng.customer;

public record CustomerRegistrationRequest(
        String firstName,
        String lastName,
        String email
) {
}
