package com.app.customer.services;

import com.app.customer.domain.AccountDto;
import com.app.customer.domain.CardDto;
import com.app.customer.provider.AccountsProvider;
import com.app.customer.provider.CardsProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final AccountsProvider accountProvider;
    private final CardsProvider cardsProvider;

    public List<AccountDto> findCustomerAccounts(Long customerId) {
        return accountProvider.getCustomerAccounts(customerId);
    }

    public List<CardDto> findCustomerCards(Long customerId) {
        return cardsProvider.getCustomerCards(customerId);
    }
}
