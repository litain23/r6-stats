package me.r6_search.web.board;

import lombok.RequiredArgsConstructor;
import me.r6_search.config.UserProfileAnnotation;
import me.r6_search.domain.userprofile.UserProfile;
import me.r6_search.service.board.PostService;
import me.r6_search.web.dto.post.PostResponseDto;
import me.r6_search.web.dto.post.PostSaveRequestDto;
import me.r6_search.web.dto.post.PostSummaryDto;
import me.r6_search.web.dto.post.PostUpdateRequestDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
public class PostController {
    private final PostService postService;


    @GetMapping("/topic/{type}")
    public List<PostSummaryDto> getPostList(@PathVariable String type,
                                            @RequestParam(defaultValue = "1", required = false) int page) {
        return postService.getCategoryPostList(type, page);
    }

    @GetMapping("/post/{id}")
    public PostResponseDto getPost(@PathVariable long id,
                                   @UserProfileAnnotation UserProfile userProfile) {
        return postService.getPost(id, userProfile);
    }

    @PostMapping("/post")
    public long makePost(@RequestBody PostSaveRequestDto requestDto,
                         @UserProfileAnnotation UserProfile userProfile) {
        return postService.savePost(requestDto, userProfile);
    }

    @PutMapping("/post/{id}")
    public long modifyPost(@PathVariable long id,
                           @RequestBody PostUpdateRequestDto requestDto,
                           @UserProfileAnnotation UserProfile userProfile) {
        return postService.modifyPost(id, requestDto, userProfile);
    }

    @DeleteMapping("/post/{id}")
    public long deletePost(@PathVariable long id,
                           @UserProfileAnnotation UserProfile userProfile) {
        return postService.deletePost(id, userProfile);
    }

    @PostMapping("/post/{id}/recommend/")
    public long toggleRecommendPost(@PathVariable long id,
                              @UserProfileAnnotation UserProfile userProfile) {
        return postService.toggleRecommendPost(id, userProfile);
    }
}
