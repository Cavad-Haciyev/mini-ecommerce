package com.example.bank.dto.user;

import com.example.bank.dto.login.LoginRequest;
import com.example.bank.model.User;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserConverter {

    public User convertToUser(UserRequest userRequest){

        User user = new User();
        user.setUsername(userRequest.getUserName());
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setRoles(userRequest.getRoles());
        return user;
    }
    public User convertUserResponseToUser(UserResponse userResponse){
        User user = new User();
        user.setUsername(userResponse.getUserName());
        user.setFirstName(userResponse.getFirstName());
        user.setLastName(userResponse.getLastName());
        user.setEmail(userResponse.getEmail());
        user.setPassword(userResponse.getPassword());
        user.setCard(userResponse.getCard());
        return user;
    }
    public UserResponse convertUserToUserResponse(User  user){
        UserResponse userResponse = new UserResponse();
        userResponse.setUserName(user.getUsername());
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setEmail(user.getEmail());
        userResponse.setPassword(user.getPassword());
        userResponse.setCard(user.getCard());
        userResponse.setRoles(user.getRoles());

        return userResponse;

    }
    public LoginRequest convert3(User user){
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(user.getUsername());
        loginRequest.setPassword(user.getPassword());
        return loginRequest;
    }
}
