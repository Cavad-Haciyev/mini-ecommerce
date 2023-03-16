package com.example.bank.service.card;

import com.example.bank.dto.card.*;
import com.example.bank.exception.CardNotFoundException;
import com.example.bank.model.Card;
import com.example.bank.repository.card.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepository;
    private final CardConvertor cardConvertor;

    public List<CardResponse> getAllCard(){
        return cardRepository.findAll()
                .stream()
                .map(cardConvertor::convertToCarResponse)
                .collect(Collectors.toList());
    }
    public List<Card> getAllCards(){
        return cardRepository.findAll();
    }
    public CardResponse getOneCard(Long id){
        return cardRepository.findById(id)
                .map(cardConvertor::convertToCarResponse)
                .orElseThrow(CardNotFoundException::new);
    }
    public Card getCardByCardCode(String cardCode){
        return cardRepository.findByCardCode(cardCode);
    }
    public CardResponse createCard(CardRequest cardRequest){
        Card card = cardConvertor.convertToCard(cardRequest);
        cardRepository.save(card);
        return cardConvertor.convertToCarResponse(card);
    }
    public CardDepositResponse deposit(CardDepositRequest cardDepositRequest){
        Card byCardCode = cardRepository.findByCardCode(cardDepositRequest.getCardCode());
        if (byCardCode==null){
            throw new CardNotFoundException();
        }
        else {
            byCardCode.setBalance(byCardCode.getBalance().add(cardDepositRequest.getAmount()));
            cardRepository.save(byCardCode);

        }
        return cardConvertor.convertToCardDepozitResponse(byCardCode);
    }
}
