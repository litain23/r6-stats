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
    public Long saveUser(SignUpRequestDto requestDto) {
        try {
            if(isSamePassword(requestDto) && hasSameUsername(requestDto)) {
                PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

                UserProfile newUser = UserProfile.builder()
                        .username(requestDto.getUsername())
                        .password(passwordEncoder.encode(requestDto.getPassword()))
                        .email(requestDto.getEmail())
                        .isEnabled(true)
                        .build();

                return userProfileRepository.save(newUser).getId();
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e);
        }
        return -1L;
    }

    private boolean isSamePassword(SignUpRequestDto signUpRequestDto) {
        String password = signUpRequestDto.getPassword();
        String password2 = signUpRequestDto.getPassword2();

        if(!password.equals(password2)) {
            throw new IllegalArgumentException("two password is different");
        } else {
            return true;
        }
    }

    private boolean hasSameUsername(SignUpRequestDto signUpRequestDto) {
        String username = signUpRequestDto.getUsername();
        UserProfile userProfile = userProfileRepository.findByUsername(username).orElse(null);
        if(userProfile != null) {
            throw  new IllegalArgumentException("already exists same username");
        } else {
            return true;
        }
    }
}
