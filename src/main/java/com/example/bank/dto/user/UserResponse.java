package com.example.bank.dto.user;

import com.example.bank.model.Card;
import com.example.bank.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Card card;
    private List<Role> roles;
}
