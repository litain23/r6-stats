package org.example.springboot.r6api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UbiApiTest {
    @Autowired
    UbiApi ubiApi;

    @Test
    public void 존재하는_유저_랭크_정보_가져오기() {

    }
}
