package org.example.springboot.service;

import lombok.RequiredArgsConstructor;
import org.example.springboot.domain.userprofile.UserProfile;
import org.example.springboot.domain.userprofile.UserProfileRepository;
import org.example.springboot.web.dto.SignUpRequestDto;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserProfileService {
    private final UserProfileRepository userProfileRepository;

    @Transactional
    public Long saveUser(SignUpRequestDto requestDto) throws IllegalArgumentException {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        UserProfile newUser = UserProfile.builder()
                .username(requestDto.getUsername())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .email(requestDto.getEmail())
                .isEnabled(true)
                .build();

        return userProfileRepository.save(newUser).getId();
    }
}