package com.example.bank.dto.order;

import com.example.bank.dto.OrderReq;
import com.example.bank.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
  private Long cartId;

}
