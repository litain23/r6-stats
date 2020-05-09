package org.example.springboot.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponseDto {
    private String message;
    private int status;

    public ErrorResponseDto() {
    }

    public ErrorResponseDto(String message) {
        this.message = message;
    }

    public ErrorResponseDto(String message, int status) {
        this.message = message;
        this.status = status;
    }
}
