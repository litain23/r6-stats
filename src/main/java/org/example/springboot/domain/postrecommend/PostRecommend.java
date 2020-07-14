package org.example.springboot.domain.postrecommend;

import org.example.springboot.domain.post.Post;
import org.example.springboot.domain.userprofile.UserProfile;

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
