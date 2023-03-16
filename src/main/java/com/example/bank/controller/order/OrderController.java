package com.example.bank.controller.order;

import com.example.bank.dto.order.OrderRequest;
import com.example.bank.dto.order.OrderResponse;
import com.example.bank.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/orders")
    public ResponseEntity<List<OrderResponse>> getAllOrder(){
        return ResponseEntity.ok(orderService.getAllOrder());
    }
    @GetMapping("/{uuid}")
    public ResponseEntity<OrderResponse> getOneOrder(@PathVariable String uuid){
        return ResponseEntity.ok(orderService.getOneOrder(uuid));
    }
    @PostMapping("/add")
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest orderRequest){
        return ResponseEntity.ok(orderService.createOrder(orderRequest));
    }
}
