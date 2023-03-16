package com.example.bank.exception;

public class ShoppingCartNotFoundException extends RuntimeException{
    public ShoppingCartNotFoundException shoppingCartNotFoundException(){
        return new ShoppingCartNotFoundException();
    }
}
