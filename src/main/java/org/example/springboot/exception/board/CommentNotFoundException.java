package org.example.springboot.exception.board;

public class CommentNotFoundException extends BoardException{
    public CommentNotFoundException(String message) {
        super(message);
    }
}
