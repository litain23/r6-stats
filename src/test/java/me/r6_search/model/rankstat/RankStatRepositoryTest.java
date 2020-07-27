package me.r6_search.model.rankstat;

import me.r6_search.r6api.dto.RankStatDto;
import me.r6_search.model.player.Player;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
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

    private RankStatDto rankStatDto;
    @Before
    public void setUp() {
        player = entityManager.persist(
                Player.builder()
                        .userId("testId")
                        .platform("uplay")
                        .profileId("test_profile_id")
                        .build()
        );

        rankStatDto = RankStatDto.builder()
                .maxRank(20) .rank(18) .abandons(1) .death(10) .kills(20) .losses(5)
                .maxMmr(2500) .mmr(2000) .region("global") .season(18) .wins(10) .build();
    }

    @Test
    public void When_SaveRankStat_Expect_Good() {
        RankStat rankStat = RankStat.builder()
                .dto(rankStatDto).player(player).build();

        rankStatRepository.save(rankStat);
        RankStat found = entityManager.find(RankStat.class, rankStat.getId());

        assertThat(found).isEqualTo(rankStat);
    }

    @Test(expected = ConstraintViolationException.class)
    public void When_SaveRankStatPlayerIsNull_Expect_ConstraintViolation() {
        RankStat rankStat = RankStat.builder().dto(rankStatDto).build();
        rankStatRepository.save(rankStat);
    }
}
