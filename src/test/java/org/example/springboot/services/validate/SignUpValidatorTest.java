package org.example.springboot.services.validate;

import org.example.springboot.domain.userprofile.UserProfile;
import org.example.springboot.domain.userprofile.UserProfileRepository;
import org.example.springboot.exception.user.UserSignUpValidateException;
import org.example.springboot.service.validate.SignUpValidator;
import org.example.springboot.web.dto.SignUpRequestDto;
import org.graalvm.compiler.lir.LIRInstruction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SignUpValidatorTest {

    @InjectMocks
    SignUpValidator signUpValidator;

    @Mock
    UserProfileRepository userProfileRepository;

    String testEmail = "test@test.com";
    String testPassword = "1234";
    String username = "test";

    UserProfile userProfile;
    String notDuplicatedName = "new_username";

    @Before
    public void setUp() {
        userProfile = UserProfile.builder()
                .email(testEmail)
                .password(testPassword)
                .isEnabled(true)
                .username(username)
                .build();

        when(userProfileRepository.findByUsername(userProfile.getUsername())).thenReturn(
                Optional.of(userProfile)
        );

        when(userProfileRepository.findByUsername(notDuplicatedName)).thenReturn(
                Optional.ofNullable(null)
        );
    }

   @Test
   public void When_SignUpParamIsGood_Expect_True() {
       SignUpRequestDto dto = new SignUpRequestDto();
       dto.setEmail("something@test.com");
       dto.setPassword("1234");
       dto.setPassword2("1234");
       dto.setUsername(notDuplicatedName);

       Boolean isValid = signUpValidator.isValid(dto, null);
       assertThat(isValid).isEqualTo(true);
   }

    @Test(expected = UserSignUpValidateException.class)
    public void When_SignUpUsernameIsDuplicate_Expect_UserSignUpValidateException() {
        SignUpRequestDto dto = new SignUpRequestDto();
        dto.setEmail("something@test.com");
        dto.setPassword("1234");
        dto.setPassword2("1234");
        dto.setUsername(userProfile.getUsername());

        signUpValidator.isValid(dto, null);
    }

    @Test(expected = UserSignUpValidateException.class)
    public void When_SignUpPasswordIsDiffEachOther_Expect_UserSignUpValidateException() {
        SignUpRequestDto dto = new SignUpRequestDto();
        dto.setEmail("something@test.com");
        dto.setPassword("1234");
        dto.setPassword2("12341");
        dto.setUsername(notDuplicatedName);

        signUpValidator.isValid(dto, null);
    }
}
