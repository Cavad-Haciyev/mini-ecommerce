package com.example.bank.exception;

public class ShoppingCartEmptyException extends RuntimeException{
    public ShoppingCartEmptyException shoppingCartEmptyException(){
        return new ShoppingCartEmptyException();
    }
}
