package org.example.springboot.domain.player;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlayerRepositoryTest {
    Player player;

    @Autowired
    PlayerRepository playerRepository;

    @Before
    public void setUp() {
    }

    @After
    public void cleanUp() {
        playerRepository.delete(player);
    }

    @Test
    public void 플레이어_레파지토리_저장_검색_테스트() {
        String id = "testPlayer6789123";
        String platform = "uplay";

        player = Player.builder()
                .playerId(id)
                .platform(platform)
                .build();

        playerRepository.save(player);

        Player searchPlayer = playerRepository.findByPlatformAndAndPlayerId(platform, id);

        assertThat(searchPlayer.getId()).isEqualTo(player.getId());
    }

}
