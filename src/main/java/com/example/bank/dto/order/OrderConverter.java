package com.example.bank.dto.order;

import com.example.bank.model.Orders;
import com.example.bank.model.enums.CurrencyType;
import com.example.bank.model.enums.OrderStatus;
import com.example.bank.repository.order.OrderRepository;
import com.example.bank.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OrderConverter {

    private final OrderRepository repository;
    public Orders convertToOrder(OrderRequest orderRequest){
        Orders orders = new Orders();
        orders.setUuid(UUID.randomUUID().toString());
        orders.setCreatedAt(LocalDateTime.now());
        orders.setOrderStatus(OrderStatus.CREATED);
        orders.setCurrencyType(CurrencyType.AZN);
        return orders;
    }
    public OrderResponse convertToOrderResponse(Orders orders){
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setUuid(orders.getUuid());
        orderResponse.setCreatedAt(orders.getCreatedAt());
        orderResponse.setOrderStatus(orders.getOrderStatus());
        orderResponse.setShoppingCart(orders.getShoppingCart());
        orderResponse.setAmount(orders.getAmount());
        orderResponse.setCurrencyType(orders.getCurrencyType());
        orderResponse.setTransaction(orders.getTransaction());
        return orderResponse;
    }
    public Orders convertToOrder(OrderResponse orderResponse){
        Orders orders = new Orders();
        orders.setUuid(orderResponse.getUuid());
        orders.setCreatedAt(orderResponse.getCreatedAt());
        orders.setOrderStatus(orderResponse.getOrderStatus());
        orders.setShoppingCart(orderResponse.getShoppingCart());
        orders.setAmount(orderResponse.getAmount());
        orders.setTransaction(orderResponse.getTransaction());
        orders.setCurrencyType(orderResponse.getCurrencyType());
//        return repository.save(orders);
        return orders;
    }
}
