package com.example.bank.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException productNotFoundException(){
        return new ProductNotFoundException();
    }
}
