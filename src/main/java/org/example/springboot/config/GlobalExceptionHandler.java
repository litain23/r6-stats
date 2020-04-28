package org.example.springboot.config;

import org.example.springboot.web.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    protected ResponseEntity<ErrorResponseDto> handleBadCredentialsException(BadCredentialsException e) {
        ErrorResponseDto errorResponse = new ErrorResponseDto();
        errorResponse.setMessage(e.getMessage());
        errorResponse.setStatus(401);
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    protected  ResponseEntity<ErrorResponseDto> handleSignupException(IllegalArgumentException e) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
        errorResponseDto.setMessage(e.getMessage());
        errorResponseDto.setStatus(400);

        return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);

    }
}
