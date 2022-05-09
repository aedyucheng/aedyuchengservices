package com.aedyucheng.fraud;

import com.aedyucheng.clients.fraud.FraudCheckResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1/fraud-check")
@AllArgsConstructor
public class FraudController {

    private FraudCheckService fraudCheckService;

    @GetMapping("/{customerId}")
    public FraudCheckResponse isFraudster(@PathVariable Integer customerId){
        boolean isFraudulent = fraudCheckService.isFraudulentCustomer(customerId);

        log.info("Fraud check for customer {}", customerId);
        return new FraudCheckResponse(isFraudulent);

    }
}
