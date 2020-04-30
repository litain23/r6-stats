package org.example.springboot.web;

import lombok.RequiredArgsConstructor;
import org.example.springboot.exception.user.UserAuthenticationException;
import org.example.springboot.security.JwtTokenProvider;
import org.example.springboot.service.UserProfileService;
import org.example.springboot.web.dto.JwtRequestDto;
import org.example.springboot.web.dto.JwtResponseDto;
import org.example.springboot.web.dto.SignUpRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@CrossOrigin
@RestController
public class JwtAuthenticationController {
    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserProfileService userProfileService;

    @PostMapping("/signin")
    public ResponseEntity signin(@RequestBody JwtRequestDto authenticationRequest) {
        try {
            String username = authenticationRequest.getUsername();
            String password = authenticationRequest.getPassword();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            String token = jwtTokenProvider.generateToken(username);
            return ResponseEntity.ok(new JwtResponseDto(token));
        } catch (AuthenticationException e) {
            throw new UserAuthenticationException("Invalid id or pw");
        }
    }

    @PostMapping
    public ResponseEntity signup(@RequestBody @Valid SignUpRequestDto signUpRequestDto) {
        userProfileService.saveUser(signUpRequestDto);
        return ResponseEntity.ok("good");
    }

}

