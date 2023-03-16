package com.example.bank.dto.transaction;

import com.example.bank.model.Orders;
import com.example.bank.model.enums.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequest {
    private String orderUuid;
    private String cardName;
    private String cardCode;
    private String cardYear;
    private Long cvv;
    private TransactionStatus transactionStatus;
}
