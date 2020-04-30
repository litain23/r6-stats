package org.example.springboot.services.validate;

import org.example.springboot.domain.userprofile.UserProfile;
import org.example.springboot.domain.userprofile.UserProfileRepository;
import org.example.springboot.service.validate.UsernameDuplicationValidator;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsernameDuplicationValidatorTest {

    private UserProfile userProfile;

    @Autowired
    UsernameDuplicationValidator usernameDuplicationValidator;

    @Autowired
    UserProfileRepository userProfileRepository;

    @After
    public void cleanUp() {
    }

    @Test
    public void 회원가입_유저_네임이_동일한것이_들어올때_Validator_Return_False() {
        userProfile = UserProfile.builder()
                .password("1234")
                .email("test@test.com")
                .username("test")
                .isEnabled(true)
                .build();

        userProfileRepository.save(userProfile);


        boolean isValid = usernameDuplicationValidator.isValid("test", null);

        assertThat(isValid).isFalse();

        userProfileRepository.delete(userProfile);
    }

    @Test
    public void 회원가입_유저_네임_확인_Validator_Return_True() {
        boolean isValid = usernameDuplicationValidator.isValid("test", null);

        assertThat(isValid).isTrue();
    }
}
