package com.example.bank.repository.order;

import com.example.bank.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Orders,Long> {
    Orders findByUuid(String uuid);
}
