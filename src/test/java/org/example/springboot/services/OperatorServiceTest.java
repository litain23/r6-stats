package org.example.springboot.services;

import org.example.springboot.domain.operator.Operator;
import org.example.springboot.domain.player.Player;
import org.example.springboot.r6api.UbiApi;
import org.example.springboot.r6api.dto.OperatorDto;
import org.example.springboot.service.OperatorService;
import org.example.springboot.web.dto.OperatorListResponseDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OperatorServiceTest {

    @InjectMocks
    OperatorService operatorService;

    @Mock
    UbiApi ubiApi;

    Player player = Player.builder()
            .platform("uplay")
            .userId("piliot")
            .build();


    List<OperatorDto> operatorDtoList;
    OperatorDto ash;

    @Before
    public void setUp() {
        operatorDtoList = new ArrayList<>();
        ash = OperatorDto.builder()
                .category("atk") .death(10) .headShot(5) .kills(10) .meleeKills(0) .name("ash") .operatorIndex("3:2")
                .roundLost(3) .roundWon(3) .timePlayed(600) .totalXp(7000) .build();
        operatorDtoList.add(ash);

        when(ubiApi.getOperatorsStat(player.getPlatform(), player.getUserId())).thenReturn(
                operatorDtoList
        );
    }

    @Test
    public void When_GetOperatorList_Expect_OperatorResponseDtoList() {
        List<OperatorListResponseDto> operatorListResponseDtoList = operatorService.getOperatorStatList(player.getPlatform(), player.getUserId(), -1);

        OperatorListResponseDto responseDto = operatorListResponseDtoList.get(0);
        assertThat(ash.getCategory()).isEqualTo(responseDto.getCategory());
        assertThat(ash.getDeath()).isEqualTo(responseDto.getDeath());
        assertThat(ash.getHeadShot()).isEqualTo(responseDto.getHeadShot());
        assertThat(ash.getKills()).isEqualTo(responseDto.getKills());
        assertThat(ash.getMeleeKills()).isEqualTo(responseDto.getMeleeKills());
        assertThat(ash.getName()).isEqualTo(responseDto.getName());
        assertThat(ash.getOperatorIndex()).isEqualTo(responseDto.getOperatorIndex());
        assertThat(ash.getRoundLost()).isEqualTo(responseDto.getRoundLost());
        assertThat(ash.getRoundWon()).isEqualTo(responseDto.getRoundWon());
        assertThat(ash.getTotalXp()).isEqualTo(responseDto.getTotalXp());
    }

    @Test
    public void When_SaveAutoOperatorList_Expect_Good() {

    }

}
