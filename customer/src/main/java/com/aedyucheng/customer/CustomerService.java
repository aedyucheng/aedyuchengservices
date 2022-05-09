package com.aedyucheng.customer;

import com.aedyucheng.amqp.RabbitMQMessageProducer;
import com.aedyucheng.clients.fraud.FraudCheckResponse;
import com.aedyucheng.clients.fraud.FraudClient;
import com.aedyucheng.clients.notification.NotificationClient;
import com.aedyucheng.clients.notification.NotificationRequest;
import com.aedyucheng.clients.notification.NotificationResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public record CustomerService(CustomerRepository customerRepository,
                              RestTemplate restTemplate,
                              FraudClient fraudClient,
//                              NotificationClient notificationClient,
                              RabbitMQMessageProducer rabbitMQMessageProducer) {
    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();

        // todo: check if email is valid

        // todo: check if eamil is taken


        customerRepository.saveAndFlush(customer);

        // check if customer is fraudster
        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());


        if(fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("Fraudster");
        }

        // todo: send notification
        NotificationRequest notificationRequest = NotificationRequest.builder()
                .toCustomerId(customer.getId())
                .toCustomerEmail(customer.getEmail())
                .message("HI, Registration success!")
                .build();

        rabbitMQMessageProducer.publish(
                notificationRequest,
                "internal.exchange",
                "internal.notification.routing-key");
    }
}
