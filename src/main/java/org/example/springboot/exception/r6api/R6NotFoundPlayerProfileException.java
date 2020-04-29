package org.example.springboot.exception.r6api;

public class R6NotFoundPlayerProfileException extends R6ErrorException{
    public R6NotFoundPlayerProfileException(String message) {
        super(message);
    }
}
