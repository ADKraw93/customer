package com.app.customer.controller;

import com.app.customer.domain.CustomerDto;
import com.app.customer.services.CustomersListGenerator;
import com.app.customer.services.CustomersMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RefreshScope
@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CustomerController {
    private final CustomersListGenerator generator;
    private final CustomersMapper mapper;

    @Value("${application.allow-get-customers}")
    private boolean allowGetCustomers;

    @GetMapping("/customer/{idCustomer}")
    public ResponseEntity<CustomerDto> getCustomerData(@PathVariable Long idCustomer) throws CustomerNotFoundException {
        if(!allowGetCustomers) {
            log.info("Getting customer is disabled");
            throw  new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Getting customer is disabled");
        }
        System.out.println("getCustomerData used");
        return ResponseEntity.ok(mapper.mapToCustomerDto(generator.findCustomer(idCustomer)));
    }
}
