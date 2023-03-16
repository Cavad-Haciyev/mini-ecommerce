package com.example.bank.dto.transaction;

import com.example.bank.dto.order.OrderResponse;
import com.example.bank.model.Orders;
import com.example.bank.model.enums.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponse {
    private TransactionStatus transactionStatus;
    private OrderResponse orders;
}
