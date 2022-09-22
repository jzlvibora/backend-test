package com.example.auth.controller;

import com.example.auth.model.Post;
import com.example.auth.model.User;
import com.example.auth.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    @RequestMapping()
//    @ResponseBody
    public String currentUserName(Principal principal) {
        return principal.getName();
    }
}
