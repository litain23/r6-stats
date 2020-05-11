package org.example.springboot.services;

import org.example.springboot.domain.player.PlayerRepository;
import org.example.springboot.service.RankPvpService;
import org.example.springboot.web.RankPvpController;
import org.example.springboot.web.dto.RankPvpResponseDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RankPvpServiceTest {

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    RankPvpService rankPvpService;

    @Autowired
    RankPvpController rankPvpController;
    @Test
    public void test() {
//        Player player = playerRepository.getPlayerIfNotExistReturnNewEntity("uplay", "hello");
//        casualPvpRepository
        rankPvpService.getRankPvp("uplay", "piliot", true);
        rankPvpService.getRankPvp("uplay", "piliot", true);
        rankPvpService.getRankPvp("uplay", "piliot", true);

        List<RankPvpResponseDto> x = rankPvpController.getRankPvpAll("uplay", "piliot");
        System.out.println(x.size());
    }
}