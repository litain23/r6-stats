package org.example.springboot.domain.userprofile;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@ToString
@EntityListeners(AuditingEntityListener.class)
@Entity
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;
    private String email;
    private String password;
    private boolean isEnabled;
    private int apiUsingCnt;

    @CreatedDate
    private LocalDateTime createTime;

    public UserProfile() { }

    @Builder
    public UserProfile(String username, String email, String password, boolean isEnabled) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.isEnabled = isEnabled;
        this.apiUsingCnt = 0;
    }
}
