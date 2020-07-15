package org.example.springboot.exception.board;

public class PostNotFoundException extends BoardException{
    public PostNotFoundException(String message) {
        super(message);
    }
}
