package com.example.bank.dto.order;

import com.example.bank.model.Product;
import com.example.bank.model.ShoppingCart;
import com.example.bank.model.Transaction;
import com.example.bank.model.enums.CurrencyType;
import com.example.bank.model.enums.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private Long id;
    private String uuid;
    private LocalDateTime createdAt;
    private OrderStatus orderStatus;
    private BigDecimal amount;
    private ShoppingCart shoppingCart;
    private CurrencyType currencyType;
    private Transaction transaction;


}
