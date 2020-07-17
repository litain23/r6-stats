package me.r6_search.domain.post;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
//    List<Post> findAll(@Param("postType") PostType postType, Pageable pageable);
    // TODO : post 의 가장 큰 데이터인 content 를 제거하고 받아야한다.
    //  query 로 작성하면 잘 안된다 ( .. )

    List<Post> findByType(PostType postType, Pageable pageable);
    List<Post> findByNotice(boolean isNotice);
    List<Post> findByType(PostType postType);
}
