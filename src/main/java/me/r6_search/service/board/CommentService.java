package me.r6_search.service.board;

import lombok.RequiredArgsConstructor;
import me.r6_search.domain.comment.Comment;
import me.r6_search.domain.comment.CommentRepository;
import me.r6_search.domain.post.Post;
import me.r6_search.domain.post.PostRepository;
import me.r6_search.exception.board.PostNotFoundException;
import me.r6_search.web.dto.comment.CommentResponseDto;
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
    private final PostRepository postRepository;

    public List<CommentResponseDto> getCommentListAtPost(long postId) {
        List<Comment> commentList = commentRepository.findByPostId(postId);
        if(commentList == null) return Collections.EMPTY_LIST;

        commentList.sort(Comparator.comparing(Comment::getCreatedTime));
        Map<Comment, List<Comment>> childCommentMap = commentList.stream()
                .filter(comment -> comment.getParentComment() != null)
                .collect(Collectors.groupingBy(Comment::getParentComment));

        List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();
        for(Comment comment : commentList) {
            // 대댓인 경우는 parent comment 가 처리될때, 하위로 들어감
            if(comment.getParentComment() != null) continue;

            CommentResponseDto commentResponseDto = CommentResponseDto.of(comment);
            if(childCommentMap.containsKey(comment)) {
                List<CommentResponseDto> childCommentResponseDtoList =
                        childCommentMap.get(comment)
                            .stream()
                            .map(c -> CommentResponseDto.of(c))
                            .collect(Collectors.toList());
                commentResponseDto.setChildComment(childCommentResponseDtoList);
            }
            commentResponseDtoList.add(commentResponseDto);
        }
        return commentResponseDtoList;
    }

    @Transactional
    public long saveComment(CommentSaveRequestDto requestDto, UserProfile userProfile) {
        Post post = postRepository.findById(requestDto.getPostId())
                .orElseThrow(() -> new PostNotFoundException("게시글을 찾을 수 없습니다."));

        Comment comment = requestDto.toEntity(post, userProfile);

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
