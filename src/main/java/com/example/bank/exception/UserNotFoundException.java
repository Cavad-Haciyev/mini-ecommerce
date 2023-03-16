package com.example.bank.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException userNotFoundException(){
        return new UserNotFoundException();
    }
}
