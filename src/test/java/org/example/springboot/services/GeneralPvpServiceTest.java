package org.example.springboot.services;

import org.example.springboot.domain.player.Player;
import org.example.springboot.r6api.UbiApi;
import org.example.springboot.r6api.dto.GeneralPvpDto;
import org.example.springboot.service.GeneralPvpService;
import org.example.springboot.web.dto.GeneralPvpResponseDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GeneralPvpServiceTest {
    @InjectMocks
    GeneralPvpService generalPvpService;

    @Mock
    UbiApi ubiApi;

    Player player = Player.builder()
            .userId("piliot")
            .platform("uplay")
            .build();

    GeneralPvpDto generalPvpDto= GeneralPvpDto.builder()
            .bulletHit(10)
            .death(10)
            .headShot(10)
            .killAssists(20)
            .kills(100)
            .matchLost(10)
            .matchPlayed(20)
            .matchWon(10)
            .meleeKills(20)
            .penetrationKills(10)
            .revive(10)
            .timePlayed(2000)
            .build();

    @Before
    public void setUp() {
        when(ubiApi.getGeneralPvp(player.getPlatform(), player.getUserId())).thenReturn(
                generalPvpDto
        );
    }

    @Test
    public void When_GetGeneralPvp_Expect_GeneralPvpResponseDto() {
        GeneralPvpDto dto = ubiApi.getGeneralPvp(player.getPlatform(), player.getUserId());

        assertThat(generalPvpDto.getDeath()).isEqualTo(dto.getDeath());
        assertThat(generalPvpDto.getKills()).isEqualTo(dto.getKills());
        assertThat(generalPvpDto.getMatchLost()).isEqualTo(dto.getMatchLost());
        assertThat(generalPvpDto.getMatchPlayed()).isEqualTo(dto.getMatchPlayed());
        assertThat(generalPvpDto.getMatchWon()).isEqualTo(dto.getMatchWon());
        assertThat(generalPvpDto.getTimePlayed()).isEqualTo(dto.getTimePlayed());
    }

}
