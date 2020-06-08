package org.example.springboot.services;

import org.example.springboot.domain.casualpvp.CasualPvp;
import org.example.springboot.domain.player.Player;
import org.example.springboot.domain.rankpvp.RankPvp;
import org.example.springboot.domain.rankpvp.RankPvpRepository;
import org.example.springboot.r6api.UbiApi;
import org.example.springboot.r6api.dto.CasualPvpDto;
import org.example.springboot.r6api.dto.RankPvpDto;
import org.example.springboot.service.PlayerService;
import org.example.springboot.service.RankPvpService;
import org.example.springboot.web.dto.CasualPvpResponseDto;
import org.example.springboot.web.dto.RankPvpResponseDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RankPvpServiceTest {
    @InjectMocks
    RankPvpService rankPvpService;

    @Mock
    RankPvpRepository rankPvpRepository;

    @Mock
    UbiApi ubiApi;

    @Mock
    PlayerService playerService;

    Player player;

    RankPvpDto rankPvpDto = RankPvpDto.builder()
            .death(10)
            .kills(10)
            .matchLost(5)
            .matchPlayed(10)
            .matchWon(5)
            .timePlayed(100)
            .build();

    String platform = "uplay";
    String userId = "piliot";
    String profileId = "test_profile_id";

    @Before
    public void setUp() {
        player = Player.builder()
                .platform(platform)
                .userId(userId)
                .profileId(profileId)
                .build();

        when(playerService.findPlayerIfNotExistReturnNewEntity(platform, userId)).thenReturn(
                player
        );

        when(ubiApi.getRankPvp(platform, userId)).thenReturn(
                rankPvpDto
        );

        when(rankPvpRepository.save(any(RankPvp.class))).thenReturn(
                null
        );
    }

    @Test
    public void When_GetRankPvpNoSave_Expect_RankPvpResponseDto() {
        RankPvpResponseDto responseDto= rankPvpService.getRankPvp(platform, userId);

        assertThat(rankPvpDto.getCreatedTime()).isEqualTo(responseDto.getCreatedTime());
        assertThat(rankPvpDto.getKills()).isEqualTo(responseDto.getKills());
        assertThat(rankPvpDto.getMatchLost()).isEqualTo(responseDto.getMatchLost());
        assertThat(rankPvpDto.getMatchPlayed()).isEqualTo(responseDto.getMatchPlayed());
        assertThat(rankPvpDto.getMatchWon()).isEqualTo(responseDto.getMatchWon());
        assertThat(rankPvpDto.getTimePlayed()).isEqualTo(responseDto.getTimePlayed());
    }

    @Test
    public void When_SaveRankPvp_Expect_RankPvpResponseDto() {
        rankPvpService.saveRankPvp(player.getPlatform(), player.getUserId());
        RankPvp rankPvp = player.getRankPvpList().get(0);
        assertThat(rankPvp.getPlayer().getUserId()).isEqualTo(player.getUserId());
    }


    @Test
    public void When_GetRankPvpList_Expect_ListRankPvpResponseDto() {
        player.getRankPvpList().add(new RankPvp(rankPvpDto, player));
        List<RankPvpResponseDto> dtoList = rankPvpService.getRankPvpAll(player.getPlatform(), player.getUserId());
        assertThat(dtoList.size()).isEqualTo(2);
    }

}
