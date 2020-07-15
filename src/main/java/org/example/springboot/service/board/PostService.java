package org.example.springboot.service.board;

import lombok.RequiredArgsConstructor;
import org.example.springboot.domain.post.Post;
import org.example.springboot.domain.post.PostRepository;
import org.example.springboot.exception.board.PostNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    protected Post findPostById(long postId) {
        return postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException("존재하지 않는 글입니다."));
    }
}

