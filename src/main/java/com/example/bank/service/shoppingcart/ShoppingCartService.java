package com.example.bank.service.shoppingcart;

import com.example.bank.dto.OrderReq;
import com.example.bank.dto.product.ProductResponse;
import com.example.bank.dto.shoppingcart.ShoppingCartConverter;
import com.example.bank.dto.shoppingcart.ShoppingCartRequest;
import com.example.bank.dto.shoppingcart.ShoppingCartResponse;
import com.example.bank.exception.ProductNotFoundException;
import com.example.bank.exception.ProductStockLawIsOrderProductNumberException;
import com.example.bank.exception.ShoppingCartNotFoundException;
import com.example.bank.model.Orders;
import com.example.bank.model.Product;
import com.example.bank.model.ShoppingCart;
import com.example.bank.model.enums.ShoppingCartStatus;
import com.example.bank.repository.product.ProductRepository;
import com.example.bank.repository.shoppingcart.ShoppingCartRepository;
import com.example.bank.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

@RequiredArgsConstructor
@Service
public class ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final ProductService productService;
    private final ShoppingCartConverter  shoppingCartConverter;
    private final ProductRepository  productRepository;



        public ShoppingCart getOneShoppingCart(Long id){
            return shoppingCartRepository.findById(id).orElseThrow(ShoppingCartNotFoundException::new);
        }
     public ShoppingCartResponse createShoppingCart(ShoppingCartRequest request){
         List<OrderReq> orderReqs = request.getOrderReqs();
         List<Product> products = new ArrayList<>();
         ShoppingCart shoppingCart = shoppingCartConverter.requestToEntity(request);
         int a= 0;
         BigDecimal total = BigDecimal.valueOf(a);
         for (OrderReq req : orderReqs) {
             Long productNumber = req.getProductNumber();
             Product oneProducts = productService.getOneProducts(req.getProductId());
             req.setAddTime(LocalDateTime.now());
             if (oneProducts.getStock()>=productNumber){
                 products.add(oneProducts);
                 shoppingCart.setProducts(products);
                 oneProducts.setAddTime(LocalDateTime.now());
                 total = total.add(oneProducts.getPrice().multiply(BigDecimal.valueOf(productNumber)));
                 shoppingCart.setAmount(total);
                shoppingCart.setShoppingCartStatus(ShoppingCartStatus.EMPTY);
                 System.out.println(req.getAddTime());
                 shoppingCartRepository.save(shoppingCart);
             }else {
                 throw new ProductStockLawIsOrderProductNumberException("Product Stock is Low : "+ oneProducts.getName());
             }

         }


         return shoppingCartConverter.entityToResponse(shoppingCart);
     }
     public ShoppingCartResponse addProduct(ShoppingCartRequest shoppingCart,Long id){
         List<OrderReq> orderReqs = shoppingCart.getOrderReqs();
         ShoppingCart cart = shoppingCartRepository.findById(id).orElseThrow(ShoppingCartNotFoundException::new);
         if (cart!=null){
            for (OrderReq product:orderReqs){
                Product oneProducts = productService.getOneProducts(product.getProductId());
                Long productNumber = product.getProductNumber();
                cart.getProducts().add(oneProducts);
                oneProducts.setAddTime(LocalDateTime.now());
                BigDecimal add = oneProducts.getPrice().multiply(BigDecimal.valueOf(productNumber));
                cart.setAmount(cart.getAmount().add(add));
                cart.setShoppingCartStatus(ShoppingCartStatus.FULL);
                shoppingCartRepository.save(cart);

            }

         }else {
             throw new ProductNotFoundException();
         }


         return shoppingCartConverter.entityToResponse(cart);
     }
    public ShoppingCartResponse deleteProduct(ShoppingCartRequest shoppingCart,Long id){
        List<OrderReq> orderReqs = shoppingCart.getOrderReqs();
        ShoppingCart cart = shoppingCartRepository.findById(id).orElseThrow(null);
        if (cart!=null){
            for (OrderReq product:orderReqs){
                Product oneProducts = productService.getOneProducts(product.getProductId());
                Long productNumber = product.getProductNumber();
                cart.getProducts().remove(oneProducts);
                BigDecimal add = oneProducts.getPrice().multiply(BigDecimal.valueOf(productNumber));
                cart.setAmount(cart.getAmount().subtract(add));
                shoppingCartRepository.save(cart);
            }

        }else {
            throw new ShoppingCartNotFoundException();
        }


        return shoppingCartConverter.entityToResponse(cart);
    }



}



