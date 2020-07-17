package me.r6_search.domain.postrecommend;

import lombok.Builder;
import lombok.Getter;
import me.r6_search.domain.post.Post;
import me.r6_search.domain.userprofile.UserProfile;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Entity
public class PostRecommend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="post_id")
    @NotNull
    private Post post;

    @ManyToOne
    @JoinColumn(name="user_profile_id")
    @NotNull
    private UserProfile userProfile;

    public PostRecommend() {}

    @Builder
    public PostRecommend(Post post, UserProfile userProfile) {
        this.post = post;
        this.userProfile = userProfile;
    }
}
