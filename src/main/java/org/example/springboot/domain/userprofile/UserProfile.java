package org.example.springboot.domain.userprofile;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@ToString
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
