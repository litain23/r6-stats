package org.example.springboot.web;

import org.example.springboot.domain.rankstat.RankStat;
import org.example.springboot.domain.player.Player;
import org.example.springboot.domain.player.PlayerRepositoryBasic;
import org.example.springboot.service.operators.GeneralPvpService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class test {
    @Autowired
    private PlayerRepositoryBasic userRepository;

    @Autowired
    private GeneralPvpService generalPvpService;
    @Before
    public void setup() {
        generalPvpService.getGeneralPvp("uplay", "piliot");
    }


    @Test
    @Transactional
    public void x() {
        Player player = userRepository.findByPlatformAndAndUserId("uplay", "piliot");
        List<RankStat> rankStats = player.getRankList();
        if(rankStats == null) {
            System.out.println("null");
        } else if(rankStats.isEmpty()) {
            System.out.println("exist");
        }
    }
}
