package me.r6_search.web.board;

import lombok.RequiredArgsConstructor;
import me.r6_search.web.dto.comment.CommentSaveRequestDto;
import me.r6_search.config.UserProfileAnnotation;
import me.r6_search.domain.userprofile.UserProfile;
import me.r6_search.service.board.CommentService;
import me.r6_search.web.dto.comment.CommentUpdateRequestDto;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/c")
@RestController
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/comment")
    public long makeComment(@RequestBody CommentSaveRequestDto requestDto,
                            @UserProfileAnnotation UserProfile userProfile) throws IllegalAccessException {
        if(userProfile == null) {
            throw new IllegalAccessException("댓글 작성 권한이 없습니다.");
        }
        return commentService.saveComment(requestDto, userProfile);
    }


    @PutMapping("/comment/{commentId}")
    public long modifyComment(@PathVariable long commentId,
                              @RequestBody CommentUpdateRequestDto requestDto,
                              @UserProfileAnnotation UserProfile userProfile) throws IllegalAccessException {

        if(userProfile == null) {
            throw new IllegalAccessException("댓글 수정 권한이 없습니다.");
        }

        return commentService.modifyComment(commentId, requestDto, userProfile);
    }

    @DeleteMapping("/comment/{commentId}")
    public long deleteComment(@PathVariable long commentId,
                              @UserProfileAnnotation UserProfile userProfile) throws IllegalAccessException {

        if(userProfile == null) {
            throw new IllegalAccessException("댓글 삭제 권한이 없습니다.");
        }
        return commentService.deleteComment(commentId, userProfile);
    }

}
