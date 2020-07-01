package org.example.springboot.r6api;

import org.example.springboot.exception.r6api.R6NotFoundPlayerProfileException;
import org.example.springboot.r6api.dto.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class UbiApiTest {
    private static UbiApi ubiApi;
    String platform = "uplay";
    String userId = "Piliot";
    String profileId;
    LocalDateTime beforeTestTime;

    @BeforeClass
    public static void setUpApi() throws IOException {
        UbiAuthApi ubiAuthApi = UbiAuthApiTest.getUbiAuthApi();
        UbiApiParser ubiApiParser = new UbiApiParser();
        ubiApi = new UbiApi(ubiAuthApi, ubiApiParser);
    }

    @Before
    public void setUp() {
        beforeTestTime = LocalDateTime.now();
        profileId = ubiApi.getProfile(platform, userId).getProfileId();
    }

    @Test
    public void When_GetRankPvpNormalUser_Expect_Good() {
        RankPvpDto dto = ubiApi.getRankPvp(platform, profileId);
        LocalDateTime afterTestTime = LocalDateTime.now();

        assertThat(dto.getDeath()).isGreaterThanOrEqualTo(0);
        assertThat(dto.getKills()).isGreaterThanOrEqualTo(0);
        assertThat(dto.getMatchLost()).isGreaterThanOrEqualTo(0);
        assertThat(dto.getMatchPlayed()).isGreaterThanOrEqualTo(0);
        assertThat(dto.getMatchWon()).isGreaterThanOrEqualTo(0);
        assertThat(dto.getTimePlayed()).isGreaterThanOrEqualTo(0);
        assertThat(dto.getCreatedTime()).isBetween(beforeTestTime, afterTestTime);
    }

    @Test
    public void When_GetCasualPvpNormalUser_Expect_Good() {
        CasualPvpDto dto = ubiApi.getCasualPvp(platform, profileId);
        LocalDateTime afterTestTime = LocalDateTime.now();

        assertThat(dto.getDeath()).isGreaterThanOrEqualTo(0);
        assertThat(dto.getKills()).isGreaterThanOrEqualTo(0);
        assertThat(dto.getMatchLost()).isGreaterThanOrEqualTo(0);
        assertThat(dto.getMatchPlayed()).isGreaterThanOrEqualTo(0);
        assertThat(dto.getMatchWon()).isGreaterThanOrEqualTo(0);
        assertThat(dto.getTimePlayed()).isGreaterThanOrEqualTo(0);
        assertThat(dto.getCreatedTime()).isBetween(beforeTestTime, afterTestTime);
    }

    @Test
    public void When_GetGeneralPvpNormalUser_Expect_Good() {
        GeneralPvpDto dto = ubiApi.getGeneralPvp(platform, profileId);
        LocalDateTime afterTestTime = LocalDateTime.now();

        assertThat(dto.getDeath()).isGreaterThanOrEqualTo(0);
        assertThat(dto.getKills()).isGreaterThanOrEqualTo(0);
        assertThat(dto.getMatchLost()).isGreaterThanOrEqualTo(0);
        assertThat(dto.getMatchPlayed()).isGreaterThanOrEqualTo(0);
        assertThat(dto.getMatchWon()).isGreaterThanOrEqualTo(0);
        assertThat(dto.getTimePlayed()).isGreaterThanOrEqualTo(0);

        assertThat(dto.getBulletHit()).isGreaterThanOrEqualTo(0);
        assertThat(dto.getKillAssists()).isGreaterThanOrEqualTo(0);
        assertThat(dto.getHeadShot()).isGreaterThanOrEqualTo(0);
        assertThat(dto.getPenetrationKills()).isGreaterThanOrEqualTo(0);
        assertThat(dto.getMeleeKills()).isGreaterThanOrEqualTo(0);
        assertThat(dto.getRevive()).isGreaterThanOrEqualTo(0);
        assertThat(dto.getCreatedTime()).isBetween(beforeTestTime, afterTestTime);
    }

    @Test
    public void When_GetRankStatNormalUser_Expect_Good() {
        RankStatDto dto = ubiApi.getRankStat(platform, profileId, "global",-1);
        LocalDateTime afterTestTime = LocalDateTime.now();

        assertThat(dto.getRank()).isGreaterThanOrEqualTo(0);
        assertThat(dto.getMaxMmr()).isGreaterThanOrEqualTo(0);
        assertThat(dto.getMaxMmr()).isGreaterThanOrEqualTo(0);
        assertThat(dto.getMmr()).isGreaterThanOrEqualTo(0);
        assertThat(dto.getKills()).isGreaterThanOrEqualTo(0);
        assertThat(dto.getDeath()).isGreaterThanOrEqualTo(0);
        assertThat(dto.getSeason()).isGreaterThanOrEqualTo(0);
        assertThat(dto.getRegion()).isEqualTo("global");
        assertThat(dto.getWins()).isGreaterThanOrEqualTo(0);
        assertThat(dto.getLosses()).isGreaterThanOrEqualTo(0);
        assertThat(dto.getAbandons()).isGreaterThanOrEqualTo(0);
        assertThat(dto.getCreatedTime()).isBetween(beforeTestTime, afterTestTime);
    }


    @Test
    public void When_GetRankStatNormalUserBefore13Season_Expect_Good() {
        RankStatDto dto = ubiApi.getRankStat(platform, profileId, "apac", 12);
        LocalDateTime afterTestTime = LocalDateTime.now();

        assertThat(dto.getRank()).isGreaterThanOrEqualTo(0);
        assertThat(dto.getMaxMmr()).isGreaterThanOrEqualTo(0);
        assertThat(dto.getMaxMmr()).isGreaterThanOrEqualTo(0);
        assertThat(dto.getMmr()).isGreaterThanOrEqualTo(0);
        assertThat(dto.getKills()).isEqualTo(0); // 시즌 13 이전에는 Kill, Death 가 0으로 들어옴
        assertThat(dto.getDeath()).isEqualTo(0);
        assertThat(dto.getSeason()).isGreaterThanOrEqualTo(0);
        assertThat(dto.getRegion()).isEqualTo("apac");
        assertThat(dto.getWins()).isGreaterThanOrEqualTo(0);
        assertThat(dto.getLosses()).isGreaterThanOrEqualTo(0);
        assertThat(dto.getAbandons()).isGreaterThanOrEqualTo(0);
        assertThat(dto.getCreatedTime()).isBetween(beforeTestTime, afterTestTime);
    }

    @Test
    public void When_GetNormalUserProfile_Expect_Good() {
        ubiApi.getProfile(platform, userId);
    }

    @Test(expected = R6NotFoundPlayerProfileException.class)
    public void When_GetAbnormalUserProfile_Expect_R6NotFoundPlayerProfile() {
        String wrongUsername = "sjkdlujv89123";
        ubiApi.getProfile(platform, wrongUsername);
    }

    @Test
    public void When_GetOperatorList_Expect_Good() {
        List<OperatorDto> operatorDtoList = ubiApi.getOperatorsStat(platform, profileId);
        assertThat(operatorDtoList.size()).isEqualTo(54);
    }
}
