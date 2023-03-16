package com.example.bank.exception;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException orderNotFoundException(){
        return new OrderNotFoundException();
    }
}
