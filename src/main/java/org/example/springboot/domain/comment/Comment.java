package org.example.springboot.domain.comment;

import org.example.springboot.domain.post.Post;
import org.example.springboot.domain.userprofile.UserProfile;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@Entity
public class Comment {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    long id;

    @ManyToOne
    @JoinColumn(name="user_profile_id")
    @NotNull
    private UserProfile userProfile;

    @ManyToOne
    @JoinColumn(name="post_id")
    private Post post;

    private String content;

    private int like;

    @CreatedDate
    private LocalDateTime createdTime;

    @LastModifiedDate
    private LocalDateTime modifiedTime;

    public Comment() { }


}
