package com.example.bank.exception;

public class BalanceAmountIsLowException extends RuntimeException {
    public BalanceAmountIsLowException balanceAmountIsLowException(){
        return new BalanceAmountIsLowException();
    }
}
