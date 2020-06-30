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
    int currentSeason = 17;
    int playStartSeason = 10;

    Map<String, List<RankStatDto>> regionStatMap;
    String[] regionList = {"apac", "emea", "ncsa"};

    private RankStatDto makeRankStatDto(int maxMmr, String region, int season) {
        return RankStatDto.builder()
                .abandons(10) .death(10) .kills(10) .losses(10) .maxMmr(maxMmr) .maxRank(20)
                .mmr(2500) .rank(20) .region(region) .season(season) .wins(10) .build();
    }

    @Before
    public void setUp() {
        regionStatMap = new HashMap<>();
        for(String region : regionList) {
            List<RankStatDto> dtoList = regionStatMap.get(region);
            if(dtoList == null) {
                dtoList = new ArrayList<>();
                dtoList.add(null); // 1부터 시작하기 위해서 null 를 넣는다
            }

            for(int season = 1; season <= currentSeason; season++) {
                int maxMmr = 0;
                if(season >= playStartSeason) maxMmr = 2500;
                RankStatDto rankstatDto = makeRankStatDto(maxMmr, region, season);
                dtoList.add(rankstatDto);
                when(ubiApi.getRankStat(platform, userId, region, season)).thenReturn(
                        rankstatDto
                );
            }

            regionStatMap.put(region, dtoList);
        }

        when(ubiApi.getRankStat(platform, userId, "apac", -1)).thenReturn(
                makeRankStatDto(2500, "apac", 17)
        );

        player = Player.builder()
                .platform(platform)
                .userId(userId)
                .profileId(profileId)
                .build();

        when(playerService.findPlayerIfNotExistReturnNewEntity(platform, userId)).thenReturn(
                player
        );

        when(rankStatRepository.save(any(RankStat.class))).thenReturn(
                null
        );

    }

//    @Test
//    public void When_GetRankStat_Expect_ListRankStatRegionResponseDto() {
//        List<RankStatRegionResponseDto> responseDtoList = rankStatService.getRankStat(platform, userId, currentSeason);

//        for(RankStatRegionResponseDto responseDto: responseDtoList) {
//            RankStatDto rankStatDto = regionStatMap.get(responseDto.getRegion()).get(currentSeason);
//            RankStatResponseDto dto = responseDto.getRankStat().get(0);
//            assertThat(dto.getSeason()).isEqualTo(rankStatDto.getSeason());
//            assertThat(dto.getAbandons()).isEqualTo(rankStatDto.getAbandons());
//            assertThat(dto.getCreatedTime()).isEqualTo(rankStatDto.getCreatedTime());
//            assertThat(dto.getDeath()).isEqualTo(rankStatDto.getDeath());
//            assertThat(dto.getKills()).isEqualTo(rankStatDto.getKills());
//            assertThat(dto.getLosses()).isEqualTo(rankStatDto.getLosses());
//            assertThat(dto.getMaxMmr()).isEqualTo(rankStatDto.getMaxMmr());
//            assertThat(dto.getMaxRank()).isEqualTo(rankStatDto.getMaxRank());
//            assertThat(dto.getMmr()).isEqualTo(rankStatDto.getMmr());
//            assertThat(dto.getRank()).isEqualTo(rankStatDto.getRank());
//        }
//    }

//    @Test
//    public void When_GetRankStateAllSeason_Expect_ListRankStatRegionResponseDto() {
//        List<RankStatRegionResponseDto> responseDtoList = rankStatService.getRankStatAllSeason(platform, userId);
//        for(RankStatRegionResponseDto responseDto : responseDtoList) {
//            List<RankStatResponseDto> rankStatResponseDtoList = responseDto.getRankStat();
//            List<RankStatDto> rankStatDtoList = regionStatMap.get(responseDto.getRegion());
//            for(RankStatResponseDto rankStatResponseDto : rankStatResponseDtoList) {
//                RankStatDto rankStatDto = rankStatDtoList.get(rankStatResponseDto.getSeason());
//                assertThat(rankStatResponseDto.getDeath()).isEqualTo(rankStatDto.getDeath());
//                assertThat(rankStatResponseDto.getKills()).isEqualTo(rankStatDto.getKills());
//                assertThat(rankStatResponseDto.getLosses()).isEqualTo(rankStatDto.getLosses());
//                assertThat(rankStatResponseDto.getMaxMmr()).isEqualTo(rankStatDto.getMaxMmr());
//                assertThat(rankStatResponseDto.getMaxRank()).isEqualTo(rankStatDto.getMaxRank());
//                assertThat(rankStatResponseDto.getMmr()).isEqualTo(rankStatDto.getMmr());
//                assertThat(rankStatResponseDto.getRank()).isEqualTo(rankStatDto.getRank());
//            }
//        }
//    }
}
