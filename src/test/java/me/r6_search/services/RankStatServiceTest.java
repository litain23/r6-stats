package me.r6_search.services;

import me.r6_search.domain.player.PlayerRepositoryTest;
import me.r6_search.r6api.UbiApi;
import me.r6_search.service.PlayerService;
import me.r6_search.domain.player.Player;
import me.r6_search.domain.rankstat.RankStatRepository;
import me.r6_search.r6api.dto.RankStatDto;
import me.r6_search.service.RankStatService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;


@RunWith(MockitoJUnitRunner.class)
public class RankStatServiceTest {
    @InjectMocks
    RankStatService rankStatService;

    @Mock
    UbiApi ubiApi;

    @Mock
    PlayerService playerService;

    @Mock
    RankStatRepository rankStatRepository;

    Player player = PlayerRepositoryTest.normalPlayer;

    private RankStatDto makeRankStatDto(int maxMmr, String region, int season) {
        return RankStatDto.builder()
                .abandons(10) .death(10) .kills(10) .losses(10) .maxMmr(maxMmr) .maxRank(20)
                .mmr(2500) .rank(20) .region(region) .season(season) .wins(10) .build();
    }

    @Test
    public void test() {
        assertThat(true).isEqualTo(true);
    }
}
