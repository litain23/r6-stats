package org.example.springboot.domain.userprofile;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserProfileRepositoryTest {

    @Autowired
    UserProfileRepository userProfileRepository;

    @Test
    public void 유저_레파지토리_저장_테스트() {
        UserProfile userProfile =
                UserProfile.builder()
                .email("aaa@naver.com")
                .password("password")
                .username("hello")
                .isEnabled(true)
                .build();
        userProfileRepository.save(userProfile);

        List<UserProfile> userProfileList = userProfileRepository.findAll();

        assertThat(userProfileList.get(0).getEmail()).isEqualTo("aaa@naver.com");

    }

}
