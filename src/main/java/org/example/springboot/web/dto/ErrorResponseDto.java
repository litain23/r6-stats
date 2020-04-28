package org.example.springboot.web.dto;

import lombok.Data;

@Data
public class ErrorResponseDto {
    private String message;
    private int status;
}
