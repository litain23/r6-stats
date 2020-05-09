package org.example.springboot.services;


import org.junit.Test;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

public class PasswordEncoderTest {
    @Test
    public void encodeTest() {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        String password = "password";
        String encPassword = "{noop}password";

        assertThat(passwordEncoder.matches(password, encPassword)).isTrue();
    }
}
