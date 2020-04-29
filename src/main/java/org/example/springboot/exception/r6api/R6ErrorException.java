package org.example.springboot.exception.r6api;

public class R6ErrorException extends RuntimeException{
    public R6ErrorException(String message) {
        super(message);
    }

    public R6ErrorException(Exception ex) {
        super(ex);
    }
}
