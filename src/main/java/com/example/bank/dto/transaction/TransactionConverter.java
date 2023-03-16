package com.example.bank.dto.transaction;

import com.example.bank.dto.order.OrderConverter;
import com.example.bank.model.Transaction;
import com.example.bank.model.enums.TransactionStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TransactionConverter {
    private final OrderConverter orderConverter;

    public Transaction convertToTransaction(TransactionRequest transactionRequest){
        Transaction transaction = new Transaction();
        transaction.setCardName(transactionRequest.getCardName());
        transaction.setCardCode(transactionRequest.getCardCode());
        transaction.setCardYear(transactionRequest.getCardYear());
        transaction.setCvv(transactionRequest.getCvv());
        return transaction;
    }
    public TransactionResponse convertToTransactionResponse(Transaction transaction){
        TransactionResponse transactionResponse = new TransactionResponse();
        transactionResponse.setTransactionStatus(transaction.getTransactionStatus());
        transactionResponse.setOrders(orderConverter.convertToOrderResponse(transaction.getOrders()));
        return transactionResponse;
    }
    public TransactionCreationResponse convertToCreatResponse(Transaction transaction){
        TransactionCreationResponse transactionCreationResponse = new TransactionCreationResponse();
        transactionCreationResponse.setTransactionStatus(transaction.getTransactionStatus());
        return transactionCreationResponse;

    }
}
