package me.r6_search.web.dto.post;

import lombok.Data;
import me.r6_search.domain.post.Post;
import me.r6_search.web.dto.comment.CommentResponseDto;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PostResponseDto {
    long postId;
    String author;
    String title;
    String content;
    int viewCnt;
    int recommendCnt;
    boolean isRecommend;
    List<CommentResponseDto> commentList;
    LocalDateTime createdTime;

    public static PostResponseDto of(Post post) {
        PostResponseDto responseDto = new PostResponseDto();
        responseDto.setAuthor(post.getUserProfile().getUsername());
        responseDto.setPostId(post.getId());
        responseDto.setTitle(post.getTitle());
        responseDto.setContent(post.getContent());
        responseDto.setViewCnt(post.getViewCnt());
        responseDto.setRecommendCnt(post.getRecommendCnt());
        responseDto.setViewCnt(post.getViewCnt());
        responseDto.setCreatedTime(post.getCreatedTime());
        responseDto.isRecommend = false;
        return responseDto;
    }
}
