package com.example.bank.dto.card;

import com.example.bank.model.enums.CardType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardResponse {
    private CardType cardType;
    private String holderName;
    private String cardCode;
    private String cardYear;
    private Long cvv;
    private BigDecimal balance;
}
