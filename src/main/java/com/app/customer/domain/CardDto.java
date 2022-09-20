package com.app.customer.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CardDto {
    private long id;
    private String cardNrb;
    private String currency;
    private String type;
}
