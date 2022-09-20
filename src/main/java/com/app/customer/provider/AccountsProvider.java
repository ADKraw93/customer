package com.app.customer.provider;

import com.app.customer.connector.AccountsConnector;
import com.app.customer.domain.AccountDto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountsProvider {
    private final AccountsConnector accountsConnector;

    //@HystrixCommand(fallbackMethod = "fallbackGetAccounts")
    public List<AccountDto> getCustomerAccounts(Long customerId) {
        return accountsConnector.getAccounts(customerId)
                .getAccountsDto()
                /*.stream()
                .map(account -> new AccountDto(
                        account.getNrb(),
                        account.getCurrency(),
                        account.getAvailableFunds()))
                .collect(Collectors.toList())*/;
    }

    /*private List<AccountDto> fallbackGetAccounts(Long customerId) {
        return Collections.emptyList();
    }*/
}
