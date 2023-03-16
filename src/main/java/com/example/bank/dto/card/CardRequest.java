package com.example.bank.dto.card;

import com.example.bank.model.enums.CardType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardRequest {

    private CardType cardType;
    private String holderName;
    @Size(max = 16,min = 16,message = "card code is not correct ")
    private String cardCode;
    private String cardYear;
    @Size(min = 3,max = 3,message = "cvv is not correct")
    private Long cvv;
    private BigDecimal balance;

}
