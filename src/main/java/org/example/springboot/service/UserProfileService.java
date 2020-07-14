package org.example.springboot.service;

import lombok.RequiredArgsConstructor;
import org.example.springboot.domain.userprofile.UserProfile;
import org.example.springboot.domain.userprofile.UserProfileRepository;
import org.example.springboot.domain.userrole.UserRole;
import org.example.springboot.web.dto.SignUpRequestDto;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;

@RequiredArgsConstructor
@Service
public class UserProfileService {
    private final UserProfileRepository userProfileRepository;
    private final MailService mailService;
    private static final String RANDOM_CODE = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    @Transactional
    public Long saveUser(SignUpRequestDto requestDto) throws IllegalArgumentException {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

//        String emailCode = generateEmailCode(18);
        String emailCode = "123";
        UserProfile newUser = UserProfile.builder()
                .username(requestDto.getUsername())
                .email(requestDto.getEmail())
                .emailCode(emailCode)
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .build();
        UserRole userRole = new UserRole();
        userRole.setRoleName("USER");
        newUser.setRoles(Arrays.asList(userRole));

        mailService.sendEmailAuthenticationCode(requestDto.getUsername(), requestDto.getEmail(), emailCode);

        return userProfileRepository.save(newUser).getId();
    }

    @Transactional
    public boolean authenticateEmail(String username, String code) {
        UserProfile userProfile = userProfileRepository.findByUsernameAndEmailAuthenticateCode(username, code);
        if(userProfile != null) {
            userProfile.setEmailAuthenticated(true);
            userProfile.getRoles().add(new UserRole("AUTHENTICATED_USED"));
            return true;
        }
        return false;
    }


    private String generateEmailCode(int len) {
        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder(len);
        for( int i = 0; i < len; i++ )
            sb.append( RANDOM_CODE.charAt( rnd.nextInt(RANDOM_CODE.length()) ) );
        return sb.toString();
    }
}
