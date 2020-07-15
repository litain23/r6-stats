package org.example.springboot.domain.comment;

import lombok.Builder;
import lombok.Getter;
import org.example.springboot.domain.post.Post;
import org.example.springboot.domain.userprofile.UserProfile;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
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

    @CreatedDate
    private LocalDateTime createdTime;

    @LastModifiedDate
    private LocalDateTime modifiedTime;

//    public Comment() { }

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
