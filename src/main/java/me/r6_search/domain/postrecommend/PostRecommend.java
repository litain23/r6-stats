package me.r6_search.domain.postrecommend;

import me.r6_search.domain.post.Post;
import me.r6_search.domain.userprofile.UserProfile;

import javax.persistence.*;

@Entity
public class PostRecommend {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;

    @OneToOne
    private Post post;

    @OneToOne
    private UserProfile userProfile;
}
