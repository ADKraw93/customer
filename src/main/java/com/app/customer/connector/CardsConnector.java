package com.app.customer.connector;

import com.app.customer.configuration.RibbonConfiguration;
import com.app.customer.domain.CardsListDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;

//@RibbonClient(name = "cards", configuration = RibbonConfiguration.class)
@FeignClient(name = "cards", fallback = CardsConnectorFallback.class) //
public interface CardsConnector {
    @GetMapping("/v1/cards")
    CardsListDto getCards(@RequestParam("customerId") Long customerId);
}

@Slf4j
@Component
class CardsConnectorFallback implements CardsConnector {
    @Override
    public CardsListDto getCards(Long customerId) {
        log.warn("Cannot get cards for customerId: " + customerId);
        return new CardsListDto(Collections.emptyList());
    }
}