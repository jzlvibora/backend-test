package com.example.auth.exception;

import javax.naming.AuthenticationException;

public class JwtTokenMalformedException extends AuthenticationException {
    public static final long serialVersionUID=1L;
    public JwtTokenMalformedException(String msg){
        super(msg);
    }
}
