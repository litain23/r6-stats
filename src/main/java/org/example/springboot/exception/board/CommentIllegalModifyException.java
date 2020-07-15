package org.example.springboot.exception.board;

public class CommentIllegalModifyException extends BoardException{
   public CommentIllegalModifyException(String message) {
        super(message);
    }
}
