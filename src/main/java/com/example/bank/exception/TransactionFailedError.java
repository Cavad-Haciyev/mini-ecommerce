package com.example.bank.exception;

public class TransactionFailedError extends RuntimeException {
    public TransactionFailedError transactionFailedError(){
        return new TransactionFailedError();
    }
}
