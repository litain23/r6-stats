package org.example.springboot.exception.user;

public class UserSignUpValidateException extends UserException {
    public UserSignUpValidateException(String message) {
        super(message);
    }
}
