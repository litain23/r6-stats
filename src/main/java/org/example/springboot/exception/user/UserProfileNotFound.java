package org.example.springboot.exception.user;

public class UserProfileNotFound extends UserException{
    public UserProfileNotFound(String message) {
        super(message);
    }
}
