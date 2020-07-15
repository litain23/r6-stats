package me.r6_search.domain.comment;

import lombok.Builder;
import lombok.Getter;
import me.r6_search.domain.post.Post;
import me.r6_search.domain.userprofile.UserProfile;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PARENT_COMMENT_ID")
    private Comment parentComment;

    @OneToMany(mappedBy = "parentComment", cascade = CascadeType.ALL)
    private List<Comment> childComment = new ArrayList<>();

    public Comment() {}

    @Builder
    public Comment(UserProfile userProfile, Post post, String content, Comment parentComment) {
        this.userProfile = userProfile;
        this.post = post;
        this.content = content;
        this.parentComment = parentComment;
    }

    @CreatedDate
    private LocalDateTime createdTime;

    @LastModifiedDate
    private LocalDateTime modifiedTime;

    public void addChildComment(Comment comment) {
        this.childComment.add(comment);
    }

    public void setParentComment(Comment comment) {
        this.parentComment = comment;
    }

    public void updateContent(String content) {
        this.content = content;
    }
}
