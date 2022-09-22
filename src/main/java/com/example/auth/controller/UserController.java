package com.example.auth.controller;

import com.example.auth.model.Post;
import com.example.auth.model.User;
import com.example.auth.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
@Autowired
UserRepository userRepository;
    @RequestMapping()
//    @ResponseBody
    public User currentUserName(Principal principal) {

       String username = principal.getName();
       User user = userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException(username));
       return user;

    }
}
