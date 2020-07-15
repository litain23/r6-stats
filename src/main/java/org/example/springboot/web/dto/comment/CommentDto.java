package org.example.springboot.web.dto.comment;

import lombok.Data;
import org.example.springboot.domain.comment.Comment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class CommentDto {
    long commentId;
    String username;
    String content;
    List<CommentDto> childCommentDto;
    LocalDateTime modifiedTime;

    public static CommentDto of(Comment comment) {
        CommentDto dto = new CommentDto();
        dto.setCommentId(comment.getId());
        dto.setContent(comment.getContent());
        dto.setModifiedTime(comment.getModifiedTime());
        dto.setUsername(comment.getUserProfile().getUsername());
        dto.setChildCommentDto(new ArrayList<>());
        return dto;
    }
}
