package me.r6_search.domain.rankpvp;

import me.r6_search.r6api.dto.RankPvpDto;
import me.r6_search.domain.player.Player;
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
public class RankPvpRepositoryTest {
    // TODO: CasualPvp와 내용이 똑같음, 단지 뭘 저장하느냐의 차이.. 합치거나 클래스로 묶어서 관리해야할 듯

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private RankPvpRepository rankPvpRepository;

    private Player player;

    private RankPvpDto rankPvpDto;

    @Before
    public void setUp() {
        player = entityManager.persist(
                Player.builder()
                        .userId("testId")
                        .platform("uplay")
                        .profileId("test_profile_id")
                        .build()
        );

        rankPvpDto = RankPvpDto.builder()
                .death(10)
                .kills(10)
                .matchLost(10)
                .matchPlayed(20)
                .matchWon(10)
                .timePlayed(100)
                .build();
    }

    @After
    public void cleanUp() {
        entityManager.clear();
    }

    @Test
    public void When_SaveRankPvp_Expect_Good() {
        RankPvp rankPvp = RankPvp.builder()
                .rankPvpDto(rankPvpDto)
                .player(player)
                .build();

        rankPvpRepository.save(rankPvp);

        RankPvp findRankPvp = entityManager.find(RankPvp.class, rankPvp.getId());
        assertThat(findRankPvp).isEqualTo(rankPvp);
    }

    @Test
    public void When_FindRankPvpUsingPlayerId_Expect_Good() {
        RankPvpDto dto1 = RankPvpDto.builder() .death(10) .kills(10) .matchLost(10) .matchPlayed(20) .matchWon(10) .timePlayed(100) .build();
        RankPvpDto dto2 = RankPvpDto.builder() .death(20) .kills(20) .matchLost(15) .matchPlayed(30) .matchWon(15) .timePlayed(300) .build();

        RankPvp rankPvp1 = RankPvp.builder().rankPvpDto(dto1).player(player).build();
        RankPvp rankPvp2 = RankPvp.builder().rankPvpDto(dto2).player(player).build();

        rankPvpRepository.save(rankPvp2);
        rankPvpRepository.save(rankPvp1);

        List<RankPvp> findRankPvp = rankPvpRepository.findByPlayer(player);
        assertThat(findRankPvp.size()).isEqualTo(2);
        for(RankPvp cp : findRankPvp) {
            assertThat(cp.getPlayer().getId()).isEqualTo(player.getId());
        }
    }

    @Test(expected = ConstraintViolationException.class)
    public void When_SaveRankPvpPlayerIsNull_Expect_ViolationException() {
        RankPvp rankPvp = RankPvp.builder().rankPvpDto(rankPvpDto).build();
        rankPvpRepository.save(rankPvp);
    }
}
