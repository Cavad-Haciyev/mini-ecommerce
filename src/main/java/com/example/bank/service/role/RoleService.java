package com.example.bank.service.role;

import com.example.bank.model.Role;
import com.example.bank.repository.role.RoleRepository;
import com.example.bank.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
    private final UserService userService;

    public Role saveRole(Role role){
        return roleRepository.save(role);
    }

    public List<Role> getAllRole(){
        return roleRepository.findAll();
    }
}
