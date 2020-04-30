package org.example.springboot.exception.user;

public class UserValidateException extends UserException {
    public UserValidateException(String message) {
        super(message);
    }
}
