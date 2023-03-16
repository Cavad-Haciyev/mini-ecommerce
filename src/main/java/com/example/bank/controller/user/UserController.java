package com.example.bank.controller.user;
import com.example.bank.config.JwtResponse;
import com.example.bank.config.JwtUtil;
import com.example.bank.dto.login.LoginRequest;
import com.example.bank.dto.user.UserRequest;
import com.example.bank.dto.user.UserResponse;
import com.example.bank.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    @PostMapping("/newUser")
    public ResponseEntity<UserResponse> register(@RequestBody UserRequest userRequest){
        return ResponseEntity.ok(userService.register(userRequest));
    }
    @GetMapping("/oneUser/{username}")
    public ResponseEntity<UserResponse> getOneUser(@PathVariable String username){
        return ResponseEntity.ok(userService.getOneUser(username));
    }
    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> getAllUser(){
        return ResponseEntity.ok(userService.getAllUser());
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) throws Exception {
        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));

        }catch (UsernameNotFoundException usernameNotFoundException){
            usernameNotFoundException.printStackTrace();
            throw new Exception("Bad Credential");
        }

        UserDetails userDetails = this.userService.loadUserByUsername(loginRequest.getUsername());

        String token =  this.jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

}
