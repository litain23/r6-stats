package org.example.springboot.web.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.springboot.service.validate.UsernameUnique;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@ToString
@Setter
@Getter
public class SignUpRequestDto {
    @NotBlank
    @UsernameUnique
    private String username;

    @Email(message = "email should be valid")
    private String email;

    private String password;
    private String password2;
}
