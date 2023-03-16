package com.example.bank.dto.card;

import com.example.bank.model.Card;
import org.springframework.stereotype.Component;

@Component
public class CardConvertor {

    public Card convertToCard(CardRequest cardRequest){
        Card card = new Card();
        card.setCardType(cardRequest.getCardType());
        card.setHolderName(cardRequest.getHolderName());
        card.setCardCode(cardRequest.getCardCode());
        card.setCardYear(cardRequest.getCardYear());
        card.setCvv(cardRequest.getCvv());
        card.setBalance(cardRequest.getBalance());
        return card;
    }
    public CardResponse convertToCarResponse(Card card){
        CardResponse cardResponse = new CardResponse();
        cardResponse.setCardType(card.getCardType());
        cardResponse.setHolderName(card.getHolderName());
        cardResponse.setCardCode(card.getCardCode());
        cardResponse.setCardYear(card.getCardYear());
        cardResponse.setCvv(card.getCvv());
        cardResponse.setBalance(card.getBalance());
        return cardResponse;
    }
    public Card convert(CardResponse cardResponse){
        Card card = new Card();
        card.setCardCode(cardResponse.getCardCode());
        card.setCardType(cardResponse.getCardType());
        card.setBalance(cardResponse.getBalance());
        return card;
    }
    public Card convertToCardDepozitRequest(CardDepositRequest cardDepositRequest) {
        Card card = new Card();
        card.setCardCode(cardDepositRequest.getCardCode());
        card.setBalance(cardDepositRequest.getAmount());
        return card;

    }
    public CardDepositResponse convertToCardDepozitResponse(Card card){
        CardDepositResponse cardDepositResponse = new CardDepositResponse();
        cardDepositResponse.setBalance(card.getBalance());
        return cardDepositResponse;
    }

}
