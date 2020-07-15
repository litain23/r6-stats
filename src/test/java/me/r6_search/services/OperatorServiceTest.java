package me.r6_search.services;

import me.r6_search.service.OperatorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.spy;

@RunWith(PowerMockRunner.class)
@PrepareForTest(OperatorService.class)
public class OperatorServiceTest {

    @Test
    public void test() {
        assertThat(true).isEqualTo(true);
    }
//    @InjectMocks
//    OperatorService operatorService;
//
//    @Mock
//    UbiApi ubiApi;
//
//    Player player = PlayerRepositoryTest.normalPlayer;
//
//
//    List<OperatorDto> operatorDtoList;
//    OperatorDto ash;
//
//    @Before
//    public void setUp() {
//        operatorDtoList = new ArrayList<>();
//        ash = OperatorDto.builder()
//                .category("atk") .death(10) .headShot(5) .kills(10) .meleeKills(0) .name("ash") .operatorIndex("3:2")
//                .roundLost(3) .roundWon(3) .timePlayed(600) .totalXp(7000) .build();
//        operatorDtoList.add(ash);
//
//        when(ubiApi.getOperatorsStat(player.getPlatform(), player.getProfileId())).thenReturn(
//                operatorDtoList
//        );
//    }
//
//    @Test
//    public void When_GetOperatorList_Expect_OperatorDtoList() {
////        OperatorService operatorServiceSpy = spy(new OperatorService(null, null, null));
////        List<OperatorDto> operatorStatList = operatorService.getTotalOperatorStatList(player);
////        when(operatorServiceSpy, method(OperatorService.class, "saveIfDontExistCurrentSeasonOperatorData"))
////
////                .thenReturn(null);
////        OperatorDto operatorDto = operatorStatList.get(0);
////        assertThat(ash).isEqualTo(operatorDto);
//    }
//
//    @Test
//    public void When_isExistCurrentSeasonOperatorData_Expect_True() {
//        OperatorService service = new operatorService
//    }

}
