package org.example.springboot.web.dto;

import lombok.Getter;

@Getter
public class JwtRequestDto {
    private String username;
    private String password;

    public JwtRequestDto() { }

    public JwtRequestDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
