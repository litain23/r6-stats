package org.example.springboot.services;

import org.example.springboot.domain.casualpvp.CasualPvp;
import org.example.springboot.domain.casualpvp.CasualPvpRepository;
import org.example.springboot.domain.player.Player;
import org.example.springboot.domain.player.PlayerRepository;
import org.example.springboot.domain.player.PlayerRepositoryTest;
import org.example.springboot.r6api.UbiApi;
import org.example.springboot.r6api.UbiAuthApiTest;
import org.example.springboot.r6api.dto.CasualPvpDto;
import org.example.springboot.service.CasualPvpService;
import org.example.springboot.service.PlayerService;
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
    CasualPvpRepository casualPvpRepository;

    @Mock
    UbiApi ubiApi;

    Player player = PlayerRepositoryTest.normalPlayer;

    CasualPvpDto casualPvpDto = CasualPvpDto.builder()
            .death(10)
            .kills(10)
            .matchLost(5)
            .matchPlayed(10)
            .matchWon(5)
            .timePlayed(100)
            .build();

    @Before
    public void setUp() {
        when(ubiApi.getCasualPvp(player.getPlatform(), player.getProfileId())).thenReturn(
                casualPvpDto
        );

        when(casualPvpRepository.save(any(CasualPvp.class))).thenReturn(
                null
        );
    }

    @Test
    public void When_GetCasualPvp_Expect_CasualPvpResponseDto() {
        CasualPvpDto dto = casualPvpService.getCasualPvp(player);

        assertThat(casualPvpDto.getDeath()).isEqualTo(dto.getDeath());
        assertThat(casualPvpDto.getKills()).isEqualTo(dto.getKills());
        assertThat(casualPvpDto.getMatchLost()).isEqualTo(dto.getMatchLost());
        assertThat(casualPvpDto.getMatchPlayed()).isEqualTo(dto.getMatchPlayed());
        assertThat(casualPvpDto.getMatchWon()).isEqualTo(dto.getMatchWon());
        assertThat(casualPvpDto.getTimePlayed()).isEqualTo(dto.getTimePlayed());
    }

    @Test
    public void When_SaveCasualPvp_Expect_CasualPvpResponseDto() {
        casualPvpService.saveCasualPvp(player);
        CasualPvp casualPvp = player.getCasualPvpList().get(0);
        assertThat(casualPvp.getPlayer().getUserId()).isEqualTo(player.getUserId());
    }

    @Test
    public void When_GetCasualPvpList_Expect_ListCasualPvpResponseDto() {
        player.getCasualPvpList().add(new CasualPvp(casualPvpDto, player));
        List<CasualPvpDto> dtoList = casualPvpService.getCasualPvpAll(player);
        assertThat(dtoList.size()).isEqualTo(player.getCasualPvpList().size() + 1);
    }

}
