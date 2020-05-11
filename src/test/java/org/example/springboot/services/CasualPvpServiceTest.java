package org.example.springboot.services;

import org.example.springboot.domain.casualpvp.CasualPvpRepository;
import org.example.springboot.domain.player.PlayerRepository;
import org.example.springboot.service.CasualPvpService;
import org.example.springboot.web.dto.CasualPvpResponseDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CasualPvpServiceTest {

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    CasualPvpRepository casualPvpRepository;

    @Autowired
    CasualPvpService casualPvpService;
    @Test
    public void test() throws InterruptedException {
//        Player player = playerRepository.getPlayerIfNotExistReturnNewEntity("uplay", "hello");
//        casualPvpRepository
        casualPvpService.getCasualPvp("uplay", "piliot", true);
        int i = 0;
        int sum = 0;
        while(i < 10000000) {
            i+=2;
            sum += i;
        }
        casualPvpService.getCasualPvp("uplay", "piliot", true);
        casualPvpService.getCasualPvp("uplay", "piliot", true);

        List<CasualPvpResponseDto> x = casualPvpService.getCasualPvpAll("uplay", "piliot");
        for(CasualPvpResponseDto xx : x) {
            System.out.println(xx);
        }
    }
}
