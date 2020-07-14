package org.example.springboot.web.board;

import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1")
@RestController
public class CommentController {

    @GetMapping("/post/{id}/comments")
    public Object getCommentList(@PathVariable int id) {
        return "";
    }

    @PostMapping("/post/{id}/comment")
    public Object makeComment(@PathVariable int id) {
        return "";
    }


    @PutMapping("/post/{postId}/comment/{commentId}")
    public Object modifyComment(@PathVariable int postId,
                                  @PathVariable int commentId) {

        return "";
    }

    @DeleteMapping("/post/{postId}/comment/{commentId}")
    public Object deleteComment(@PathVariable int postId,
                                  @PathVariable int commentId) {
        return "";
    }

}
