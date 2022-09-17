package com.app.customer.services;

import com.app.customer.domain.Customer;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@NoArgsConstructor
@Data
@Service
public class CustomersListGenerator {
    public List<Customer> generateCustomers() {
        List<Customer> list = List.of(
                new Customer(1421, "John", "Smith"),
                new Customer(634, "Johnny", "Rambo"),
                new Customer(521, "Sara", "Grey")
        );
        return list;
    }

    public Customer findCustomer(long id) {
        List<Customer> list = generateCustomers();
        Customer result = list.stream()
                .filter(customer -> customer.getId()==id)
                .findAny()
                .orElse(new Customer());
        return result;
    }
}
