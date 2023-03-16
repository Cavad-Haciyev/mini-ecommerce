package com.example.bank.model.enums;

public enum CurrencyType {

    AZN("100"),
    USD("200"),
    EUR("300");

    private final String code;

    CurrencyType (String code){
        this.code = code;
    }

}
