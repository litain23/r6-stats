package org.example.springboot.services;

import org.example.springboot.domain.player.Player;
import org.example.springboot.domain.player.PlayerRepositoryTest;
import org.example.springboot.domain.rankstat.RankStat;
import org.example.springboot.domain.rankstat.RankStatRepository;
import org.example.springboot.r6api.UbiApi;
import org.example.springboot.r6api.dto.RankStatDto;
import org.example.springboot.service.PlayerService;
import org.example.springboot.service.RankStatService;
import org.example.springboot.web.dto.RankStatRegionResponseDto;
import org.example.springboot.web.dto.RankStatResponseDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;


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
