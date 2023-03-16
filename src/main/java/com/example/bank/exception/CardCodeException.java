package com.example.bank.exception;

public class CardCodeException extends RuntimeException {
    public CardCodeException cardCodeException(){
        return new CardCodeException();
    }
}
