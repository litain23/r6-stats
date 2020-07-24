package me.r6_search.service;

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

        simpleMailMessage.setText("\n회원가입 해주셔서 감사합니다 ! \n https://r6-search.me/api/v1/authenticate?username=" + username + "&code=" + code +
                "\n 위의 링크를 누르시면 인증이 됩니다.");
        mailSender.send(simpleMailMessage);
    }
}
