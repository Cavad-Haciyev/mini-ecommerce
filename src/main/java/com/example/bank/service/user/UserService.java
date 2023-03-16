package com.example.bank.service.user;

import com.example.bank.dto.card.CardConvertor;
import com.example.bank.dto.card.CardResponse;
import com.example.bank.dto.user.UserConverter;
import com.example.bank.dto.user.UserRequest;
import com.example.bank.dto.user.UserResponse;
import com.example.bank.exception.CardNotFoundException;
import com.example.bank.exception.UserNotFoundException;
import com.example.bank.model.Card;
import com.example.bank.model.User;
import com.example.bank.repository.user.UserRepository;
import com.example.bank.service.card.CardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class UserService implements UserDetailsService{

    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final CardService cardService;
    private final CardConvertor cardConvertor;
//    private final BCryptPasswordEncoder encoder;


    public UserResponse getOneUser(String username){
        User user = userRepository.findByUsername(username);
        UserResponse userResponse = userConverter.convertUserToUserResponse(user);
        return userResponse;

    }
    public List<UserResponse> getAllUser(){
       return userRepository.findAll()
               .stream()
               .map(user -> userConverter.convertUserToUserResponse(user))
               .collect(Collectors.toList());

    }

    public UserResponse register(UserRequest userRequest){
        CardResponse oneCard = cardService.getOneCard(userRequest.getCardId());
        Card convert = cardConvertor.convert(oneCard);

        if (oneCard==null){
            throw new CardNotFoundException();
        }else {
            User user = userConverter.convertToUser(userRequest);
            user.setCard(convert);
            User save = userRepository.save(user);
            return userConverter.convertUserToUserResponse(save);
        }

    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user==null){
            throw new UserNotFoundException();
        }else {
//
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),getAuthority(user));
    }
    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        });
        return authorities;
    }
}
