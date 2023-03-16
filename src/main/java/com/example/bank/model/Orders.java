package com.example.bank.model;

import com.example.bank.model.enums.CurrencyType;
import com.example.bank.model.enums.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;
    @Enumerated(EnumType.STRING)
    private CurrencyType currencyType;
    private LocalDateTime createdAt;
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    @OneToOne(mappedBy = "orders", cascade = CascadeType.MERGE)
    private Transaction transaction;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    private ShoppingCart shoppingCart;

}
