package com.app.customer.controller;

import com.app.customer.controller.response.GetCustomerProductsResponse;
import com.app.customer.domain.AccountDto;
import com.app.customer.domain.CustomerDto;
import com.app.customer.services.CustomersListGenerator;
import com.app.customer.services.CustomersMapper;
import com.app.customer.services.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
@RefreshScope
@RestController
@RequestMapping(value = "/v1/customer", produces = { MediaType.APPLICATION_JSON_VALUE })
@RequiredArgsConstructor
@CrossOrigin("*")
public class CustomerController {
    private final CustomersListGenerator generator;
    private final CustomersMapper mapper;
    private final ProductService productService;

    @Value("${application.allow-get-customers}")
    private boolean allowGetCustomers;

    @GetMapping("/{idCustomer}")
    public ResponseEntity<CustomerDto> getCustomerData(@PathVariable Long idCustomer) throws CustomerNotFoundException {
        if(!allowGetCustomers) {
            log.info("Getting customer is disabled");
            throw  new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Getting customer is disabled");
        }
        System.out.println("getCustomerData used");
        return ResponseEntity.ok(mapper.mapToCustomerDto(generator.findCustomer(idCustomer)));
    }

    @GetMapping("/{customerId}/products")
    public GetCustomerProductsResponse getCustomerProducts(@PathVariable Long customerId) {
        CustomerDto customerDto = mapper.mapToCustomerDto(generator.findCustomer(customerId));

        List<AccountDto> customerAccounts = productService.findCustomerAccounts(customerId);

        return GetCustomerProductsResponse.builder()
                .customerId(customerDto.getId())
                .fullName(customerDto.getFirstName() + " " + customerDto.getLastName())
                .accounts(customerAccounts)
                .build();
    }
}
