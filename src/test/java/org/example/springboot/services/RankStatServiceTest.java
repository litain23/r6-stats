package org.example.springboot.services;

import org.example.springboot.domain.player.Player;
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

    Player player;
    String platform = "uplay";
    String userId = "piliot";
    String profileId = "test_profile_id";

    private RankStatDto makeRankStatDto(int maxMmr, String region, int season) {
        return RankStatDto.builder()
                .abandons(10) .death(10) .kills(10) .losses(10) .maxMmr(maxMmr) .maxRank(20)
                .mmr(2500) .rank(20) .region(region) .season(season) .wins(10) .build();
    }

    private RankStatDto makeRankStatDtoBefore12Season(int maxMmr, String region, int season) {
        return RankStatDto.builder()
                .abandons(10) .death(0) .kills(0) .losses(10) .maxMmr(maxMmr) .maxRank(20)
                .mmr(2500) .rank(20) .region(region) .season(season) .wins(10) .build();
    }

    @Before
    public void setUp() {
        when(ubiApi.getRankStat(platform, userId, "global", -1)).thenReturn(
                makeRankStatDto(2500, "global", 18)
        );

        when(ubiApi.getRankStat(platform, userId, "apac", 16)).thenReturn(
                makeRankStatDto(2500 - 16, "apac", 16)
        );

//        when(ubiApi.getRankStat(platform, userId, "global", 18)).thenReturn(
//                makeRankStatDto(2500, "global", 18)
//        }
//        for(int i=1;i<=17;i++) {
//            int maxMmr = 2500 - i;
//            if(i <= 10) maxMmr = 0;
//            RankStatDto dto = makeRankStatDto(maxMmr, "apac", i);
//
//            when(ubiApi.getRankStat(platform, userId, "apac", i)).thenReturn(
//                    dto
//            );
//        }
//
//        when(playerService.findPlayerIfNotExistReturnNewEntity(platform, userId)).thenReturn(
//                player
//        );
//
//        when(rankStatRepository.save(any(RankStat.class))).thenReturn(
//                null
//        );
//
//        player = Player.builder()
//                .platform(platform)
//                .userId(userId)
//                .profileId(profileId)
//                .build();
    }

    @Test
    public void When_GetRankStatCurrentSeason_Expect_RankStatDto() {
        List<RankStatDto> dtoList = rankStatService.getRankStat(platform, userId, -1);
        RankStatDto globalRegionDto = dtoList.get(0);
        assertThat(globalRegionDto.getKills()).isEqualTo(10);
        assertThat(globalRegionDto.getRegion()).isEqualTo("global");
        assertThat(globalRegionDto.getMaxMmr()).isEqualTo(2500);
    }

    @Test
    public void When_GetRankStatBeforeCurrentSeason_Expect_RankStatDto() {
        List<RankStatDto> dtoList = rankStatService.getRankStat(platform, userId, 16);
        RankStatDto globalRegionDto = dtoList.get(0);
        assertThat(globalRegionDto.getKills()).isEqualTo(10);
        assertThat(globalRegionDto.getRegion()).isEqualTo("apac");
        assertThat(globalRegionDto.getMaxMmr()).isEqualTo(2500 - 16);
    }
}
