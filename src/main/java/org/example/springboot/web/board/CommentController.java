package org.example.springboot.web.board;

import lombok.RequiredArgsConstructor;
import org.example.springboot.config.UserProfileAnnotation;
import org.example.springboot.domain.userprofile.UserProfile;
import org.example.springboot.service.board.CommentService;
import org.example.springboot.web.dto.comment.CommentSaveRequestDto;
import org.example.springboot.web.dto.comment.CommentUpdateRequestDto;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/post/{postId}/comments")
    public Object getCommentList(@PathVariable long postId) {
        return "";
    }

    @PostMapping("/comment")
    public Long makeComment(@RequestBody CommentSaveRequestDto requestDto,
                            @UserProfileAnnotation UserProfile userProfile) {
        return commentService.saveComment(requestDto, userProfile);
    }


    @PutMapping("/comment/{commentId}")
    public long modifyComment(@PathVariable long commentId,
                              @RequestBody CommentUpdateRequestDto requestDto,
                              @UserProfileAnnotation UserProfile userProfile) {

        return commentService.modifyComment(commentId, requestDto, userProfile);
    }

    @DeleteMapping("/comment/{commentId}")
    public long deleteComment(@PathVariable long commentId,
                              @UserProfileAnnotation UserProfile userProfile) {
        return commentService.deleteComment(commentId, userProfile);
    }

}
