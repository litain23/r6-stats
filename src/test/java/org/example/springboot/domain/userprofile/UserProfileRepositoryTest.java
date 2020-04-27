package org.example.springboot.domain.userprofile;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserProfileRepositoryTest {

    @Autowired
    UserProfileRepository userProfileRepository;

    @After
    public void cleanup() {
        UserProfile userProfile = userProfileRepository.findByUsername("mytest123!@#").get();
        userProfileRepository.delete(userProfile);
    }

    @Test
    public void 유저_레파지토리_저장_검색_테스트() {
        UserProfile userProfile =
                UserProfile.builder()
                .email("aaa@naver.com")
                .password("password")
                .username("mytest123!@#")
                .isEnabled(true)
                .build();

        userProfileRepository.save(userProfile);

        UserProfile findProfile = userProfileRepository.findByUsername("mytest123!@#").get();

        assertThat(findProfile.getUsername()).isEqualTo(userProfile.getUsername());
    }

}
