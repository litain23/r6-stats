package me.r6_search.domain.postrecommend;

import me.r6_search.domain.userprofile.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRecommendRepository extends JpaRepository<PostRecommend, Long> {
    PostRecommend findByPostIdAndUserProfile(long postId, UserProfile userProfile);
}
