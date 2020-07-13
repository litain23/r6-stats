package org.example.springboot.web;

import lombok.RequiredArgsConstructor;
import org.example.springboot.exception.user.UserAuthenticationException;
import org.example.springboot.security.JwtTokenProvider;
import org.example.springboot.service.UserProfileService;
import org.example.springboot.web.dto.JwtRequestDto;
import org.example.springboot.web.dto.JwtResponseDto;
import org.example.springboot.web.dto.SignUpRequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody @Valid SignUpRequestDto signUpRequestDto) {
        userProfileService.saveUser(signUpRequestDto);
        return new ResponseEntity<>("{\"status\": \"good\"}", HttpStatus.OK);
    }

    @GetMapping("/user/authenticate")
    public ResponseEntity authenticateEmail(@RequestParam String username,
                                            @RequestParam String code) {
        boolean isAuthenticated = userProfileService.authenticateEmail(username, code);
        if(isAuthenticated) {
            return new ResponseEntity("{\"message\": \"Email Authentication Success\"}", HttpStatus.OK);
        } else {
            return new ResponseEntity("{\"message\": \"Email Authentication fail, Check email again\"}", HttpStatus.BAD_REQUEST);
        }
    }

}

