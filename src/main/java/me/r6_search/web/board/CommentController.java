package me.r6_search.web.board;

import lombok.RequiredArgsConstructor;
import me.r6_search.web.dto.comment.CommentSaveRequestDto;
import me.r6_search.config.UserProfileAnnotation;
import me.r6_search.domain.userprofile.UserProfile;
import me.r6_search.service.board.CommentService;
import me.r6_search.web.dto.comment.CommentUpdateRequestDto;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/post/{postId}/comment")
    public Object getCommentList(@PathVariable long postId) {
        return "";
    }

    @PostMapping("/comment")
    public long makeComment(@RequestBody CommentSaveRequestDto requestDto,
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
