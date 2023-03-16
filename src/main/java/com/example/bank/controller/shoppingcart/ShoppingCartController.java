package com.example.bank.controller.shoppingcart;

import com.example.bank.dto.product.ProductResponse;
import com.example.bank.dto.shoppingcart.ShoppingCartRequest;
import com.example.bank.dto.shoppingcart.ShoppingCartResponse;
import com.example.bank.service.shoppingcart.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/shoppingCart")
@RestController
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    @PostMapping
    public ShoppingCartResponse createShoppingCart(@RequestBody ShoppingCartRequest request) {
        return shoppingCartService.createShoppingCart(request);
    }
    @PutMapping("/addProduct/{id}")
    public ShoppingCartResponse addProduct(@PathVariable Long id,@RequestBody ShoppingCartRequest request){
        return shoppingCartService.addProduct(request,id);
    }
    @PutMapping("/deleteProduct/{id}")
    public ShoppingCartResponse deleteProduct(@PathVariable Long id,@RequestBody ShoppingCartRequest request){
        return shoppingCartService.deleteProduct(request,id);
    }

}
