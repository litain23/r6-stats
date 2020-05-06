package org.example.springboot.domain.casualpvp;

import org.example.springboot.domain.casualpvp.CasualPvp;
import org.example.springboot.domain.casualpvp.CasualPvpRepository;
import org.example.springboot.domain.player.Player;
import org.example.springboot.domain.player.PlayerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CasualPvpTest {

    @Autowired
    CasualPvpRepository casualPvpRepository;

    @Autowired
    PlayerRepository playerRepository;

    @Test
    public void test() {
        Player player = playerRepository.getPlayerIfNotExistReturnNewEntity("uplay", "piliot");

        CasualPvp casualPvp = CasualPvp.builder()
                .death(10)
                .kills(10)
                .matchLost(10)
                .matchPlayed(10)
                .matchWon(10)
                .timePlayed(10)
                .build();

        casualPvp.setPlayer(player);

        casualPvpRepository.save(casualPvp);

        List<CasualPvp> list = casualPvpRepository.findAll();


    }
}
