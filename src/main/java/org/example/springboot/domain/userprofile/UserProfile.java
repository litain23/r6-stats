package org.example.springboot.domain.userprofile;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.example.springboot.domain.userrole.UserRole;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@ToString
@Getter
@EntityListeners(AuditingEntityListener.class)
@Entity
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;
    private String email;
    private String password;
    private String emailAuthenticateCode;
    private boolean isEmailAuthenticated;
    private boolean isUbiAuthenticated;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "roles")
    private List<UserRole> roles;

    @CreatedDate
    private LocalDateTime createTime;

    public UserProfile() { }

    @Builder
    public UserProfile(String username, String email, String password, String emailCode) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.emailAuthenticateCode = emailCode;
        this.isEmailAuthenticated = false;
        this.isUbiAuthenticated = false;
    }

    public void setRoles(List<UserRole> roles) {
        this.roles = roles;
    }

    public void setEmailAuthenticated(boolean isEmailAuthenticated) {
        this.isEmailAuthenticated = isEmailAuthenticated;
    }

    public void setUbiAuthenticated(boolean isUbiAuthenticated) {
        this.isUbiAuthenticated = isUbiAuthenticated;
    }
}
