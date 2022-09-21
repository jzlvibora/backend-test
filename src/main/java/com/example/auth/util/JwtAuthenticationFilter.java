package com.example.auth.util;

import com.example.auth.auth.UserAuthService;
import com.example.auth.exception.JwtTokenMalformedException;
import com.example.auth.exception.JwtTokenMissingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserAuthService userAuthService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header=request.getHeader("Authorization");

        if(header==null || !header.startsWith("HTTP_TOKEN")){
            try {
                throw new JwtTokenMissingException("No JWT token found in the request headers");
            } catch (JwtTokenMissingException e) {
                e.printStackTrace();
            }
        }

        assert header != null;
        String token = header.substring("HTTP_TOKEN".length() + 1);
        try {
            jwtUtil.validateToken(token);
        } catch (JwtTokenMissingException | JwtTokenMalformedException e) {
            e.printStackTrace();
        }

        String username = jwtUtil.getUsername(token);

        UserDetails userDetails= userAuthService.loadUserByUsername(username);

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken= new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());

        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }

        filterChain.doFilter(request, response);
    }
    }

