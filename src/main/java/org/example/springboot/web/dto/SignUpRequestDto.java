package org.example.springboot.web.dto;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class SignUpRequestDto {
    private String username;
    private String email;
    private String password;
    private String password2;
}
