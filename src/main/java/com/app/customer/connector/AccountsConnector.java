package com.app.customer.connector;

import com.app.customer.configuration.RibbonConfiguration;
import com.app.customer.domain.AccountsListDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;

//@RibbonClient(name = "accounts", configuration = RibbonConfiguration.class)
@FeignClient(name = "accounts", fallback = AccountsConnectorFallback.class)
public interface AccountsConnector {
    @GetMapping("/v1/accounts")
    AccountsListDto getAccounts(@RequestParam("customerId") Long customerId);
}

@Slf4j
@Component
class AccountsConnectorFallback implements AccountsConnector {
    @Override
    public AccountsListDto getAccounts(Long customerId) {
        log.warn("Cannot get accounts for customerId: " + customerId);
        return new AccountsListDto(Collections.emptyList());
    }
}