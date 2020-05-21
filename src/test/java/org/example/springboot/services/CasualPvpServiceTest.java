package org.example.springboot.services;

import org.example.springboot.domain.casualpvp.CasualPvp;
import org.example.springboot.domain.casualpvp.CasualPvpRepository;
import org.example.springboot.domain.player.Player;
import org.example.springboot.domain.player.PlayerRepository;
import org.example.springboot.r6api.UbiApi;
import org.example.springboot.r6api.UbiAuthApiTest;
import org.example.springboot.r6api.dto.CasualPvpDto;
import org.example.springboot.service.CasualPvpService;
import org.example.springboot.web.dto.CasualPvpResponseDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.MapKeyColumn;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CasualPvpServiceTest {

    @InjectMocks
    CasualPvpService casualPvpService;

    @Mock
    PlayerRepository playerRepository;

    @Mock
    CasualPvpRepository casualPvpRepository;

    @Mock
    UbiApi ubiApi;

    Player player;

    CasualPvpDto casualPvpDto = CasualPvpDto.builder()
            .death(10)
            .kills(10)
            .matchLost(5)
            .matchPlayed(10)
            .matchWon(5)
            .timePlayed(100)
            .build();

    String platform = "uplay";
    String userId = "piliot";

    @Before
    public void setUp() {
        player = Player.builder()
                .platform("uplay")
                .userId("piliot")
                .build();

        when(playerRepository.getPlayerIfNotExistReturnNewEntity(platform, userId)).thenReturn(
                player
        );

        when(ubiApi.getCasualPvp(platform, userId)).thenReturn(
                casualPvpDto
        );

        when(casualPvpRepository.save(any(CasualPvp.class))).thenReturn(
                null
        );
    }

    @Test
    public void When_GetCasualPvpNotSave_Expect_CasualPvpResponseDto() {
        CasualPvpResponseDto responseDto = casualPvpService.getCasualPvp(player.getPlatform(), player.getUserId());

        assertThat(casualPvpDto.getDeath()).isEqualTo(responseDto.getDeath());
        assertThat(casualPvpDto.getKills()).isEqualTo(responseDto.getKills());
        assertThat(casualPvpDto.getMatchLost()).isEqualTo(responseDto.getMatchLost());
        assertThat(casualPvpDto.getMatchPlayed()).isEqualTo(responseDto.getMatchPlayed());
        assertThat(casualPvpDto.getMatchWon()).isEqualTo(responseDto.getMatchWon());
        assertThat(casualPvpDto.getTimePlayed()).isEqualTo(responseDto.getTimePlayed());
    }

    @Test
    public void When_SaveCasualPvp_Expect_CasualPvpResponseDto() {
        casualPvpService.saveCasualPvp(player.getPlatform(), player.getUserId());
        CasualPvp casualPvp = player.getCasualPvpList().get(0);
        assertThat(casualPvp.getPlayer().getUserId()).isEqualTo(player.getUserId());
    }

    @Test
    public void When_GetCasualPvpList_Expect_ListCasualPvpResponseDto() {
        player.getCasualPvpList().add(new CasualPvp(casualPvpDto, player));
        List<CasualPvpResponseDto> dtoList = casualPvpService.getCasualPvpAll(player.getPlatform(), player.getUserId());
        assertThat(dtoList.size()).isEqualTo(2);
    }

}
