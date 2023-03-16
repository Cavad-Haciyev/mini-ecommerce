package com.example.bank.exception;

public class CvvException extends RuntimeException{
    public CvvException cvvException(){
        return new CvvException();
    }
}
