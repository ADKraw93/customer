package com.app.customer.controller.response;

import com.app.customer.domain.AccountDto;
import com.app.customer.domain.CardDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetCustomerProductsResponse {
    private Long customerId;
    private String fullName;
    private List<AccountDto> accounts;
    private List<CardDto> cards;
}
