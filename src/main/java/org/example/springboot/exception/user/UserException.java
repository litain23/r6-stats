package org.example.springboot.exception.user;

public class UserException extends RuntimeException {
    public UserException(String message) {
        super(message);
    }
}
