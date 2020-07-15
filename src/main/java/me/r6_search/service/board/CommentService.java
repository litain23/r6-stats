package me.r6_search.service.board;

import lombok.RequiredArgsConstructor;
import me.r6_search.domain.comment.Comment;
import me.r6_search.domain.comment.CommentRepository;
import me.r6_search.domain.post.Post;
import me.r6_search.web.dto.comment.CommentDto;
import me.r6_search.web.dto.comment.CommentSaveRequestDto;
import me.r6_search.domain.userprofile.UserProfile;
import me.r6_search.exception.board.CommentIllegalModifyException;
import me.r6_search.exception.board.CommentNotFoundException;
import me.r6_search.web.dto.comment.CommentUpdateRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostService postService;

    public List<CommentDto> getCommentListAtPost(int postId) {
        List<Comment> commentList = commentRepository.findByPost(postId);
        if(commentList == null) return Collections.EMPTY_LIST;

        commentList.sort(Comparator.comparing(Comment::getCreatedTime));

        Map<Comment, List<Comment>> childCommentMap = commentList.stream()
                .filter(comment -> comment.getParentComment() != null)
                .collect(Collectors.groupingBy(Comment::getParentComment));

        List<CommentDto> commentDtoList = new ArrayList<>();
        for(Comment comment : commentList) {
            CommentDto parentCommentDto = CommentDto.of(comment);
            if(childCommentMap.containsKey(comment)) {
                List<CommentDto> childCommentDtoList =
                        childCommentMap.get(comment)
                            .stream()
                            .map(c -> CommentDto.of(c))
                            .collect(Collectors.toList());
                parentCommentDto.setChildCommentDto(childCommentDtoList);
            }
            commentDtoList.add(parentCommentDto);
        }
        return commentDtoList;
    }

    @Transactional
    public long saveComment(CommentSaveRequestDto requestDto, UserProfile userProfile) {
        Post post = postService.findPostById(requestDto.getPostId());
        Comment comment = Comment.builder()
                .content(requestDto.getContent())
                .userProfile(userProfile)
                .post(post)
                .build();

        long commentId = commentRepository.save(comment).getId();

        if(requestDto.getParentCommentId() != 0) {
            Comment parentComment = commentRepository.findById(requestDto.getParentCommentId()).orElseThrow(() -> new CommentNotFoundException("댓글이 존재하지 않습니다."));
            parentComment.addChildComment(comment);
            comment.setParentComment(parentComment);
        }
        return commentId;
    }

    @Transactional
    public long modifyComment(long commentId, CommentUpdateRequestDto requestDto, UserProfile userProfile) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new CommentNotFoundException("수정할 댓글이 존재하지 않습니다."));

        if(comment.getUserProfile().getId() != userProfile.getId()) {
            throw new CommentIllegalModifyException("댓글을 수정할 권한이 없습니다.");
        }

        comment.updateContent(requestDto.getContent());
        return comment.getId();
    }

    @Transactional
    public long deleteComment(long commentId, UserProfile userProfile) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new CommentNotFoundException("수정할 댓글이 존재하지 않습니다."));

        if(comment.getUserProfile().getId() != userProfile.getId()) {
            throw new CommentIllegalModifyException("댓글을 삭제할 권한이 없습니다.");
        }
        commentRepository.delete(comment);
        return commentId;
    }
}
