package com.example.bank.dto.shoppingcart;

import com.example.bank.model.Product;
import com.example.bank.model.enums.ShoppingCartStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartResponse {
    private List<Product> products;
    private BigDecimal amount;
    private ShoppingCartStatus shoppingCartStatus;
}
