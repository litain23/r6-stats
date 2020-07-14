package org.example.springboot.domain.comment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentRepository, Long> {
    List<Comment> findByPost(int postId);
}