package com.example.bank.dto;

import com.example.bank.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderReq {
    private Long productId;
    private Long productNumber;
    private LocalDateTime addTime;
}
