package com.example.bank.model;

import com.example.bank.model.enums.CardType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING
    )
    private CardType cardType;
    private String holderName;
    private String cardCode;
    private String cardYear;
    @Column(unique = true)
    private Long cvv;
    private BigDecimal balance;
    @OneToOne(mappedBy = "card")
    private User user;

}
