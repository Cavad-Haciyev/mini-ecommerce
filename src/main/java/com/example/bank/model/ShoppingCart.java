package com.example.bank.model;

import com.example.bank.model.enums.ShoppingCartStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime createdAt;
    @OneToMany
    @JoinColumn(name = "cart_id")
    private List<Product> products;
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private ShoppingCartStatus  shoppingCartStatus;



}
