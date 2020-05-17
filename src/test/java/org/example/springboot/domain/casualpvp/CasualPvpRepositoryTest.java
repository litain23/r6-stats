package org.example.springboot.domain.casualpvp;

import org.example.springboot.domain.player.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CasualPvpRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CasualPvpRepository casualPvpRepository;

    private Player player;

    @Before
    public void setUp() {
        player = entityManager.persist(
                Player.builder()
                    .userId("testId")
                    .platform("uplay")
                    .build()
        );
    }

    @After
    public void cleanUp() {
        entityManager.clear();
    }

    @Test
    public void When_SaveCasualPvp_Expect_Good() {
        CasualPvp casualPvp = CasualPvp.builder()
                .matchPlayed(20)
                .matchWon(10)
                .matchLost(10)
                .timePlayed(20)
                .kills(20)
                .death(20)
                .player(player)
                .build();

        casualPvpRepository.save(casualPvp);

        CasualPvp findCasualPvp = entityManager.find(CasualPvp.class, casualPvp.getId());
        assertThat(findCasualPvp.getPlayer()).isEqualTo(casualPvp.getPlayer());
    }

    @Test
    public void When_FindCasualPvpUsingPlayerId_Expect_Good() {
        CasualPvp casualPvp = CasualPvp.builder().matchPlayed(20).matchWon(10).matchLost(10).timePlayed(20).kills(20).death(20).player(player).build();
        CasualPvp casualPvp1 = CasualPvp.builder().matchPlayed(20).matchWon(10).matchLost(10).timePlayed(20).kills(20).death(20).player(player).build();

        casualPvpRepository.save(casualPvp);
        casualPvpRepository.save(casualPvp1);


        List<CasualPvp> findCasualPvp = casualPvpRepository.findByPlayer(player);
        assertThat(findCasualPvp.size()).isEqualTo(2);
        for(CasualPvp cp : findCasualPvp) {
            assertThat(cp.getPlayer().getId()).isEqualTo(player.getId());
        }
    }

    @Test(expected = ConstraintViolationException.class)
    public void When_SaveCasualPvpPlayerIsNull_Expect_ViolationException() {
        CasualPvp casualPvp = CasualPvp.builder().matchPlayed(20).matchWon(10).matchLost(10).timePlayed(20).kills(20).death(20).build();
        casualPvpRepository.save(casualPvp);
    }
}
