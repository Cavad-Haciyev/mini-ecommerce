package com.example.bank.controller.transaction;

import com.example.bank.dto.transaction.TransactionCreationResponse;
import com.example.bank.dto.transaction.TransactionRequest;
import com.example.bank.dto.transaction.TransactionResponse;
import com.example.bank.service.transaction.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transaction")
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping("/add")
    public TransactionCreationResponse createTransaction(@RequestBody TransactionRequest transactionRequest){
        return transactionService.createTransaction(transactionRequest);

    }
}
