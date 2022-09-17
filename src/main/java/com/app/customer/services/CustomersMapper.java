package com.app.customer.services;

import com.app.customer.domain.Customer;
import com.app.customer.domain.CustomerDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Data
@NoArgsConstructor
@Service
public class CustomersMapper {
    public CustomerDto mapToCustomerDto(Customer customer) {
        CustomerDto result = new CustomerDto(customer.getId(), customer.getFirstName(), customer.getLastName());
        return result;
    }
}
