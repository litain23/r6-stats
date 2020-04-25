package org.example.springboot.web;

import lombok.RequiredArgsConstructor;
import org.example.springboot.security.JwtTokenUtil;
import org.example.springboot.web.dto.JwtRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@CrossOrigin
@RestController
public class JwtAuthenticationController {
    private final JwtTokenUtil jwtTokenUtil;
//    private JwtUserDetailsService userDetailsService;

    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequestDto authenticationRequest) throws Exception {
        return null;
    }

}

