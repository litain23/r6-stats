package me.r6_search.service.board;

import lombok.RequiredArgsConstructor;
import me.r6_search.domain.post.Post;
import me.r6_search.domain.post.PostRepository;
import me.r6_search.exception.board.PostNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    protected Post findPostById(long postId) {
        return postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException("존재하지 않는 글입니다."));
    }
}

