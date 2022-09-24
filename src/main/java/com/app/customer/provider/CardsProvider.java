package com.app.customer.provider;

import com.app.customer.connector.CardsConnector;
import com.app.customer.domain.CardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CardsProvider {
    private final CardsConnector cardsConnector;

    //@HystrixCommand(fallbackMethod = "fallbackGetCards")
    public List<CardDto> getCustomerCards(Long customerId) {
        System.out.println(cardsConnector.getCards(customerId)
                .getCardsDto());
        return cardsConnector.getCards(customerId)
                .getCardsDto();
    }

    /*private List<CardDto> fallbackGetCards(Long customerId) {
        return Collections.emptyList();
    }*/
}
