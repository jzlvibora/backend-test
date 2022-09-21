package com.example.auth.exception;

public class InvalidUserCredentialsException extends RuntimeException{
    public static final long serialVersionUID=1L;
    public InvalidUserCredentialsException(String msg){
        super(msg);
    }
}
