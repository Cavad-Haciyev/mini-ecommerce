package com.example.bank.dto.transaction;

import com.example.bank.model.enums.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionCreationResponse {
    private TransactionStatus transactionStatus;
}
