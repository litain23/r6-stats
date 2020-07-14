package org.example.springboot.domain.userrole;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Entity
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String roleName;

    public UserRole() { }

    public UserRole(String roleName) {
        this.roleName = roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
