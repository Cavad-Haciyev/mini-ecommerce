package com.example.bank.exception;

public class CardNotFoundException extends RuntimeException{
   public CardNotFoundException cardNotFoundException(){
        return new CardNotFoundException();
    }
}
