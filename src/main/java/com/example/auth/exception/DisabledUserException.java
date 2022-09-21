package com.example.auth.exception;

public class DisabledUserException extends RuntimeException{
    public static final long serialVersionUID=1L;
    public DisabledUserException(String msg){
        super(msg);
    }
}
