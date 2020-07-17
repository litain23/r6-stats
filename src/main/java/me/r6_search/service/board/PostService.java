package me.r6_search.service.board;

import lombok.RequiredArgsConstructor;
import me.r6_search.domain.post.Post;
import me.r6_search.domain.post.PostRepository;
import me.r6_search.domain.post.PostType;
import me.r6_search.domain.postrecommend.PostRecommend;
import me.r6_search.domain.postrecommend.PostRecommendRepository;
import me.r6_search.domain.userprofile.UserProfile;
import me.r6_search.exception.board.BoardException;
import me.r6_search.exception.board.PostIllegalModifyException;
import me.r6_search.exception.board.PostNotFoundException;
import me.r6_search.web.dto.post.PostResponseDto;
import me.r6_search.web.dto.post.PostSaveRequestDto;
import me.r6_search.web.dto.post.PostSummaryDto;
import me.r6_search.web.dto.post.PostUpdateRequestDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;
    private final PostRecommendRepository postRecommendRepository;
    private final CommentService commentService;

    private final int VIEW_POST_CNT = 20;

    @Transactional(readOnly = true)
    public List<PostSummaryDto> getCategoryPostList(String type, int page) {
        // 공지사항 가져오기
        List<Post> postSummaryList = addNoticePost();

        Pageable pageable = PageRequest.of(page - 1, VIEW_POST_CNT, Sort.Direction.DESC, "createdTime");
        postSummaryList.addAll(postRepository.findByType(PostType.valueOf(type), pageable));

        List<PostSummaryDto> postSummaryDtoList = new ArrayList<>();
        for(Post post : postSummaryList) {
            PostSummaryDto dto = PostSummaryDto.builder()
                    .author(post.getUserProfile().getUsername())
                    .title(post.getTitle())
                    .hasImg(true)
                    .createdTime(post.getCreatedTime())
                    .recommendCnt(post.getRecommendCnt())
                    .viewCnt(post.getViewCnt())
                    .isNotice(post.isNotice())
                    .postId(post.getId())
                    .build();

            postSummaryDtoList.add(dto);
        }
        return postSummaryDtoList;
    }

    private List<Post> addNoticePost() {
        List<Post> noticePostList = postRepository.findByType(PostType.valueOf("notice"));
        if(noticePostList == null) return Collections.EMPTY_LIST;
        return noticePostList;
    }


    @Transactional
    public PostResponseDto getPost(long postId, UserProfile userProfile) {
        Post post = findPostById(postId);
        PostResponseDto responseDto = PostResponseDto.of(post);
        responseDto.setCommentList(commentService.getCommentListAtPost(postId));
        post.increaseViewCnt();

        PostRecommend isRecommend = postRecommendRepository.findByPostIdAndUserProfile(postId, userProfile);
        if(isRecommend != null) {
            responseDto.setRecommend(true);
        }
        return responseDto;
    }

    @Transactional
    public long savePost(PostSaveRequestDto requestDto, UserProfile userProfile) {
        Post post = requestDto.toEntity(userProfile);

        // 임시
        // 공지는 admin 권한을 가진 사람만 가능 - 추가해야됨
        if(requestDto.getType() == "notice") throw new BoardException("권한이 없습니다");
        return postRepository.save(post).getId();
    }

    @Transactional
    public long modifyPost(long postId, PostUpdateRequestDto requestDto, UserProfile userProfile) {
        Post post = findPostById(postId);
        checkAuthenticationForModify(post, userProfile);
        post.updatePost(requestDto);
        return postId;
    }

    @Transactional
    public long deletePost(long postId, UserProfile userProfile) {
        Post deletePost = findPostById(postId);
        checkAuthenticationForModify(deletePost, userProfile);
        postRepository.delete(deletePost);
        return postId;
    }


    @Transactional
    public long toggleRecommendPost(long postId, UserProfile userProfile) {
        PostRecommend isRecommend = postRecommendRepository.findByPostIdAndUserProfile(postId, userProfile);
        if(isRecommend != null) {
            postRecommendRepository.delete(isRecommend);
        } else {
            Post post = findPostById(postId);
            postRecommendRepository.save(
                PostRecommend.builder()
                    .post(post)
                    .userProfile(userProfile)
                    .build()
            );
        }
        return postId;
    }

    private Post findPostById(long postId) {
        return postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException("존재하지 않는 글입니다."));
    }

    private void checkAuthenticationForModify(Post post, UserProfile userProfile){
        // user profile equals 이걸로 하면 안될까 생각해보기
        if(userProfile.getId() != post.getUserProfile().getId()) {
            throw new PostIllegalModifyException("변경할 수 있는 권한이 없습니다.");
        }
    }
}

