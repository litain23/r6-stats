package org.example.springboot.domain.userprofile;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Entity
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String userName;
    private String email;
    private String password;
    private boolean isActive;

    public UserProfile() { }

    @Builder
    public UserProfile(String userName, String email, String password, boolean isActive) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.isActive = isActive;
    }
}
