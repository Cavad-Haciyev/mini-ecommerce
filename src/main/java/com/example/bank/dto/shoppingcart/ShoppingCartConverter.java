package com.example.bank.dto.shoppingcart;

import com.example.bank.model.ShoppingCart;
import com.example.bank.model.enums.ShoppingCartStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ShoppingCartConverter {
    public ShoppingCart requestToEntity(ShoppingCartRequest request){
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setCreatedAt(LocalDateTime.now());
        return shoppingCart;
    }
    public ShoppingCartResponse entityToResponse(ShoppingCart shoppingCart){
        ShoppingCartResponse shoppingCartResponse = new ShoppingCartResponse();
        shoppingCartResponse.setProducts(shoppingCart.getProducts());
        shoppingCartResponse.setShoppingCartStatus(shoppingCart.getShoppingCartStatus());
        shoppingCartResponse.setAmount(shoppingCart.getAmount());
        return shoppingCartResponse;
    }
}
