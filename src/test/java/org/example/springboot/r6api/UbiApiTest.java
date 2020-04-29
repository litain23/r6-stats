package org.example.springboot.r6api;

import org.example.springboot.exception.r6api.R6NotFoundPlayerProfileException;
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

    @Autowired
    UbiAuthApi ubiAuthApi;

    @Test
    public void 존재하는_유저_랭크_정보_가져오기() {
        String id = "piliot";
        String platform = "uplay";

        String rankStat = ubiApi.getRankStat(platform, id, -1);


    }

    @Test(expected = R6NotFoundPlayerProfileException.class)
    public void 존재하지않는_유저_검색() {
        String id = "nxjklc91230-xczsad";
        String operatorStat = ubiApi.getOperatorsStat("uplay", id);
    }
}
