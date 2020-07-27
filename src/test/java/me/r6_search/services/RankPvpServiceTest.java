package me.r6_search.services;

import me.r6_search.model.player.PlayerRepositoryTest;
import me.r6_search.model.rankpvp.RankPvp;
import me.r6_search.model.rankpvp.RankPvpRepository;
import me.r6_search.r6api.UbiApi;
import me.r6_search.service.RankPvpService;
import me.r6_search.model.player.Player;
import me.r6_search.r6api.dto.RankPvpDto;
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

    Player player = PlayerRepositoryTest.normalPlayer;

    RankPvpDto rankPvpDto = RankPvpDto.builder()
            .death(10)
            .kills(10)
            .matchLost(5)
            .matchPlayed(10)
            .matchWon(5)
            .timePlayed(100)
            .build();


    @Before
    public void setUp() {
        when(ubiApi.getRankPvp(player.getPlatform(), player.getProfileId())).thenReturn(
                rankPvpDto
        );

        when(rankPvpRepository.save(any(RankPvp.class))).thenReturn(
                null
        );
    }

    @Test
    public void When_GetRankPvpNoSave_Expect_RankPvpResponseDto() {
        RankPvpDto dto = rankPvpService.getRankPvp(player);
        assertThat(rankPvpDto).isEqualTo(dto);
    }

    @Test
    public void When_SaveRankPvp_Expect_RankPvpDto() {
        rankPvpService.saveRankPvp(player);
        RankPvp rankPvp = player.getRankPvpList().get(0);
        assertThat(rankPvp.getPlayer().getUserId()).isEqualTo(player.getUserId());
    }


    @Test
    public void When_GetRankPvpList_Expect_ListRankPvpDto() {
        player.getRankPvpList().add(new RankPvp(rankPvpDto, player));
        List<RankPvpDto> dtoList = rankPvpService.getRankPvpAll(player);
        assertThat(dtoList.size()).isEqualTo(player.getRankPvpList().size() + 1);
    }

}
