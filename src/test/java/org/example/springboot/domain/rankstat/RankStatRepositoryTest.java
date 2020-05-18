package org.example.springboot.domain.rankstat;

import org.example.springboot.domain.player.Player;
import org.example.springboot.domain.rankpvp.RankPvpRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.core.Constants;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RankStatRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private RankStatRepository rankStatRepository;

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

    @Test
    public void When_SaveRankStat_Expect_Good() {
        RankStat rankStat = RankStat.builder()
                .maxRank(10).rank(10).abandons(0).death(10).kills(10).losses(10).mmr(1500)
                .maxMmr(1500).region("apac").season(17).wins(10).player(player).build();

        rankStatRepository.save(rankStat);
        RankStat found = entityManager.find(RankStat.class, rankStat.getId());

        assertThat(found).isEqualTo(rankStat);
    }

    @Test(expected = ConstraintViolationException.class)
    public void When_SaveRankStatPlayerIsNull_Expect_ConstraintViolation() {
        RankStat rankStat = RankStat.builder()
                .maxRank(10).rank(10).abandons(0).death(10).kills(10).losses(10).mmr(1500)
                .maxMmr(1500).region("apac").season(17).wins(10).build();

        rankStatRepository.save(rankStat);
    }
}
