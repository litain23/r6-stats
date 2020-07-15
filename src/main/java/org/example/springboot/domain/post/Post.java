package org.example.springboot.domain.post;

import org.example.springboot.domain.comment.Comment;
import org.example.springboot.domain.player.Player;
import org.example.springboot.domain.rankpvp.RankPvp;
import org.example.springboot.domain.userprofile.UserProfile;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@EntityListeners(AuditingEntityListener.class)
@Entity
public class Post {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    long id;

    @ManyToOne
    @JoinColumn(name="user_profile_id")
    @NotNull
    private UserProfile userProfile;

    private boolean isNotice;

    private String title;

    private String content;

    private int viewCnt;

    private int recommendCnt;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> commentList = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private PostType type;

    @CreatedDate
    private LocalDateTime createdTime;

    @LastModifiedDate
    private LocalDateTime modifiedTime;

    public Post() { }


}
