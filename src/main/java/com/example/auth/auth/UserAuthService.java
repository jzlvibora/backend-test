package com.example.auth.auth;


import com.example.auth.dao.Request;
import com.example.auth.model.User;
import com.example.auth.model.UserRole;
import com.example.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserAuthService implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

   @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findByUsername(username);
        List<UserRole> userRoles = user.getUserRoles().stream().collect(Collectors.toList());

        List<GrantedAuthority> grantedAuthorities = userRoles.stream().map(r->{
            return new SimpleGrantedAuthority(r.getRole());
        }).collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(username, user.getUserPass(), grantedAuthorities);
    }

    public void saveUser(Request request){
//        if(userRepository.findByUsername(request.getUsername()).isPresent()){
//            throw new RuntimeException("User already exists");
//        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setUserPass(passwordEncoder.encode(request.getUserpwd()));
        user.setUserRoles(request.getRoles().stream().map(r->{
            UserRole userRole=new UserRole();
            userRole.setRole(r);
            return userRole;
        }).collect(Collectors.toSet()));

        userRepository.save(user);
    }
}
