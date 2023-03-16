package com.example.bank.dto.shoppingcart;

import com.example.bank.dto.OrderReq;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartRequest {
    private List<OrderReq> orderReqs;
}
