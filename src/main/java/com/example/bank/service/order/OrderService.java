package com.example.bank.service.order;

import com.example.bank.dto.OrderReq;
import com.example.bank.dto.order.OrderConverter;
import com.example.bank.dto.order.OrderRequest;
import com.example.bank.dto.order.OrderResponse;
import com.example.bank.dto.product.ProductResponse;
import com.example.bank.exception.*;
import com.example.bank.model.Orders;
import com.example.bank.model.Product;
import com.example.bank.model.ShoppingCart;
import com.example.bank.model.enums.OrderStatus;
import com.example.bank.model.enums.ShoppingCartStatus;
import com.example.bank.repository.order.OrderRepository;
import com.example.bank.repository.shoppingcart.ShoppingCartRepository;
import com.example.bank.service.product.ProductService;
import com.example.bank.service.shoppingcart.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderConverter orderConverter;
    private final ShoppingCartService shoppingCartService;
    private final ShoppingCartRepository shoppingCartRepository;

    public List<OrderResponse> getAllOrder() {
        return orderRepository.findAll()
                .stream()
                .map(orders -> orderConverter.convertToOrderResponse(orders))
                .collect(Collectors.toList());
    }

    public OrderResponse getOneOrder(String uuid) {
        Orders orders = orderRepository.findByUuid(uuid);
        if (orders == null) {
            throw new OrderNotFoundException();
        } else {
            return orderConverter.convertToOrderResponse(orders);

        }
    }

    public Orders getOrderByUuid(String uuid) {
        Orders orders = orderRepository.findByUuid(uuid);
        if (orders == null) {
            throw new OrderNotFoundException();
        } else {
            return orders;
        }
    }

    public OrderResponse createOrder(OrderRequest orderRequest) {

        ShoppingCart oneShoppingCart = shoppingCartService.getOneShoppingCart(orderRequest.getCartId());
        Orders orders = orderConverter.convertToOrder(orderRequest);
        List<Product> products = oneShoppingCart.getProducts();
        orders.setShoppingCart(oneShoppingCart);
        for (Product product : products) {
            if (LocalDateTime.now().isAfter(product.getAddTime().plusMinutes(10))) {
                products.remove(product);
                oneShoppingCart.setAmount(oneShoppingCart.getAmount().subtract(product.getPrice()));
                shoppingCartRepository.save(oneShoppingCart);
            } else {
                orders.setAmount(oneShoppingCart.getAmount());
                orders.setShoppingCart(oneShoppingCart);
                orderRepository.save(orders);
            }


        }
        return orderConverter.convertToOrderResponse(orders);
    }


    }

