package me.r6_search.web.dto.comment;

import lombok.Data;
import me.r6_search.domain.comment.Comment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class CommentResponseDto {
    long commentId;
    String username;
    String content;
    List<CommentResponseDto> childComment;
    LocalDateTime createdTime;

    public static CommentResponseDto of(Comment comment) {
        CommentResponseDto dto = new CommentResponseDto();
        dto.setCommentId(comment.getId());
        dto.setContent(comment.getContent());
        dto.setCreatedTime(comment.getCreatedTime());
        dto.setUsername(comment.getUserProfile().getUsername());
        dto.setChildComment(new ArrayList<>());
        return dto;
    }
}
