package org.example.springboot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MailService {
    private final MailSender mailSender;
    private static final String SEND_EMAIL_ADDRESS = "r6searchme@gmail.com";

    public void sendEmailAuthenticationCode(String username, String email, String code) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(email);
        simpleMailMessage.setFrom(SEND_EMAIL_ADDRESS);
        simpleMailMessage.setSubject("R6 Search Me 가입 인증 코드");
        simpleMailMessage.setText("http://127.0.0.1:8080/user/authenticate?username=" + username + "&code=" + code);
        mailSender.send(simpleMailMessage);
    }
}
