package com.example.bank.controller.card;

import com.example.bank.dto.card.CardDepositRequest;
import com.example.bank.dto.card.CardDepositResponse;
import com.example.bank.dto.card.CardRequest;
import com.example.bank.dto.card.CardResponse;
import com.example.bank.model.Card;
import com.example.bank.service.card.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/card")
public class CardController {
    private final CardService cardService;
    @GetMapping("/cards")
    public ResponseEntity<List<CardResponse>> getAllCard(){
        return ResponseEntity.ok(cardService.getAllCard());
    }
    @GetMapping("/{id}")
    public ResponseEntity<CardResponse> getOneCard(@PathVariable Long id){
        return ResponseEntity.ok(cardService.getOneCard(id));
    }
    @PostMapping("/add")
    public ResponseEntity<CardResponse> createCard(@RequestBody CardRequest cardRequest){
        return ResponseEntity.ok(cardService.createCard(cardRequest));
    }
    @PostMapping("/deposit")
    public ResponseEntity<CardDepositResponse> deposit(@RequestBody CardDepositRequest cardRequest){
        return ResponseEntity.ok(cardService.deposit(cardRequest));
    }
    @GetMapping("/cardcode/{cardCode}")
    public ResponseEntity<Card> getCardByCardCode(@PathVariable String cardCode){
        return ResponseEntity.ok(cardService.getCardByCardCode(cardCode));
    }
}
