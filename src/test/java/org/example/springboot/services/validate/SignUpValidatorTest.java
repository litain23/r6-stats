package org.example.springboot.services.validate;

import org.example.springboot.domain.userprofile.UserProfile;
import org.example.springboot.domain.userprofile.UserProfileRepository;
import org.example.springboot.exception.user.UserSignUpValidateException;
import org.example.springboot.service.validate.SignUpValidator;
import org.example.springboot.web.dto.SignUpRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SignUpValidatorTest {

    private UserProfile userProfile;

    @Autowired
    SignUpValidator signUpValidator;

    @Autowired
    UserProfileRepository userProfileRepository;

    @After
    public void cleanUp() {
        if(userProfile != null) {
            userProfileRepository.delete(userProfile);
        }
    }

    @Test
    public void 패스워드_비교_같은지_확인() {
        SignUpRequestDto dto = new SignUpRequestDto();
        dto.setUsername("hello");
        dto.setPassword2("password");
        dto.setPassword("password");
        dto.setEmail("test@test.com");

        boolean isValid = signUpValidator.isValid(dto, null);
        assertThat(isValid).isTrue();
    }

    @Test(expected = UserSignUpValidateException.class)
    public void 패스워드_다른것_Exception_확인() {
        SignUpRequestDto dto = new SignUpRequestDto();
        dto.setUsername("hello");
        dto.setPassword2("password");
        dto.setPassword("password1");
        dto.setEmail("test@test.com");

        boolean isValid = signUpValidator.isValid(dto, null);
        assertThat(isValid).isFalse();
    }

    @Test(expected = UserSignUpValidateException.class)
    public void 유저_이름_중복_확인() {
        userProfile = UserProfile.builder()
                .username("hello")
                .isEnabled(true)
                .email("test@test.com")
                .password("1234")
                .build();

        userProfileRepository.save(userProfile);

        SignUpRequestDto dto = new SignUpRequestDto();
        dto.setUsername("hello");
        dto.setPassword2("password");
        dto.setPassword("password1");
        dto.setEmail("test@test.com");

        boolean isValid = signUpValidator.isValid(dto, null);
        assertThat(isValid).isFalse();
    }
}
