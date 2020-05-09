package org.example.springboot.exception.r6api;

public class R6BadAuthenticationException extends R6ErrorException {
    public R6BadAuthenticationException(String message) {
        super(message);
    }
}
