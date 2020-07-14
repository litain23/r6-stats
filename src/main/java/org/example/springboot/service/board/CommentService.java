package org.example.springboot.service.board;

import lombok.RequiredArgsConstructor;
import org.example.springboot.domain.comment.Comment;
import org.example.springboot.domain.comment.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public List<Object> getCommentListAtPost(int postId) {
        List<Comment> commentList = commentRepository.findByPost(postId);
        if(commentList == null) return Collections.EMPTY_LIST;

        return null;
    }

    public Long makeComment(int postId) {
        return null;
    }

    public Long modifyComment(int postId, int commentId) {
        return null;
    }

    public Long deleteComment(int postId, int commentId) {
        return null;
    }
}
