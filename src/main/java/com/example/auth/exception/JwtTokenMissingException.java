package com.example.auth.exception;

import javax.naming.AuthenticationException;

public class JwtTokenMissingException extends AuthenticationException {
    public static final long serialVersionUID=1L;

    public JwtTokenMissingException(String msg) {
        super(msg);
    }
}
