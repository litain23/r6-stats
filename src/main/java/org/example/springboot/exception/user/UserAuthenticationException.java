package org.example.springboot.exception.user;

public class UserAuthenticationException extends UserException {
    public UserAuthenticationException(String message) {
        super(message);
    }
}
