package org.example.springboot.services;


import org.example.springboot.domain.userprofile.UserProfile;
import org.example.springboot.domain.userprofile.UserProfileRepository;
import org.example.springboot.security.UserProfileDetailsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserProfileDetailsServiceTest {
    @Autowired
    UserProfileRepository userProfileRepository;

    @Autowired
    UserProfileDetailsService userProfileDetailsService;

    @Test
    public void authenticate() {
        UserProfile userProfile = UserProfile.builder()
                        .email("aaa@naver.com")
                        .password("password")
                        .username("hello")
                        .isEnabled(true)
                        .build();

        userProfileRepository.save(userProfile);

        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userProfileDetailsService);
        authenticationProvider.setPasswordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder());

        List<AuthenticationProvider> authenticationProviderList = Stream.of(
                authenticationProvider
        ).collect(Collectors.toList());

        ProviderManager providerManager = new ProviderManager(authenticationProviderList);

        Authentication auth = new UsernamePasswordAuthenticationToken("hello", "{noop}password");
        assertThat(auth.isAuthenticated()).isFalse();

        auth = providerManager.authenticate(auth);

        assertThat(auth.isAuthenticated()).isTrue();



    }
}
