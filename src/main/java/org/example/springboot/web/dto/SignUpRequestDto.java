package org.example.springboot.web.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.springboot.service.validate.SignUpValid;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@ToString
@Setter
@Getter
@SignUpValid
public class SignUpRequestDto {
    @NotBlank
    private String username;

    @NotBlank
    @Email(message = "Email format is incorrect")
    private String email;

    @NotBlank
    private String password;
    @NotBlank
    private String password2;
}
