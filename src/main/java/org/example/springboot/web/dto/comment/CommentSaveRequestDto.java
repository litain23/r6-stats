package org.example.springboot.web.dto.comment;

import lombok.Data;

@Data
public class CommentSaveRequestDto {
    long postId;
    String content;
    long parentCommentId;
}