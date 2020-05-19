package org.example.springboot.r6api;

import org.example.springboot.exception.r6api.R6NotFoundPlayerProfileException;
import org.example.springboot.r6api.dto.CasualPvpDto;
import org.example.springboot.r6api.dto.GeneralPvpDto;
import org.example.springboot.r6api.dto.RankPvpDto;
import org.example.springboot.r6api.dto.RankStatDto;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;


public class UbiApiTest {
    private static UbiApi ubiApi;
    String platform = "uplay";
    String username = "piliot";
    LocalDateTime beforeTestTime;

    @BeforeClass
    public static void setUpApi() throws IOException {
        UbiAuthApi ubiAuthApi = UbiAuthApiTest.getUbiAuthApi();
        ubiApi = new UbiApi(ubiAuthApi);
    }

    @Before
    public void setUp() {
        beforeTestTime = LocalDateTime.now();
    }

    @Test
    public void When_GetRankPvpNormalUser_Expect_Good() {
        RankPvpDto dto = ubiApi.getRankPvp(platform, username);
        LocalDateTime afterTestTime = LocalDateTime.now();

        assertThat(dto.getDeath()).isGreaterThan(0);
        assertThat(dto.getKills()).isGreaterThan(0);
        assertThat(dto.getMatchLost()).isGreaterThan(0);
        assertThat(dto.getMatchPlayed()).isGreaterThan(0);
        assertThat(dto.getMatchWon()).isGreaterThan(0);
        assertThat(dto.getTimePlayed()).isGreaterThan(0);
        assertThat(dto.getCreatedTime()).isBetween(beforeTestTime, afterTestTime);
    }

    @Test
    public void When_GetCasualPvpNormalUser_Expect_Good() {
        CasualPvpDto dto = ubiApi.getCasualPvp(platform, username);
        LocalDateTime afterTestTime = LocalDateTime.now();

        assertThat(dto.getDeath()).isGreaterThan(0);
        assertThat(dto.getKills()).isGreaterThan(0);
        assertThat(dto.getMatchLost()).isGreaterThan(0);
        assertThat(dto.getMatchPlayed()).isGreaterThan(0);
        assertThat(dto.getMatchWon()).isGreaterThan(0);
        assertThat(dto.getTimePlayed()).isGreaterThan(0);
        assertThat(dto.getCreatedTime()).isBetween(beforeTestTime, afterTestTime);
    }

    @Test
    public void When_GetGeneralPvpNormalUser_Expect_Good() {
        GeneralPvpDto dto = ubiApi.getGeneralPvp(platform, username);
        LocalDateTime afterTestTime = LocalDateTime.now();

        assertThat(dto.getDeath()).isGreaterThan(0);
        assertThat(dto.getKills()).isGreaterThan(0);
        assertThat(dto.getMatchLost()).isGreaterThan(0);
        assertThat(dto.getMatchPlayed()).isGreaterThan(0);
        assertThat(dto.getMatchWon()).isGreaterThan(0);
        assertThat(dto.getTimePlayed()).isGreaterThan(0);

        assertThat(dto.getBulletHit()).isGreaterThan(0);
        assertThat(dto.getKillAssists()).isGreaterThan(0);
        assertThat(dto.getHeadShot()).isGreaterThan(0);
        assertThat(dto.getPenetrationKills()).isGreaterThan(0);
        assertThat(dto.getMeleeKills()).isGreaterThan(0);
        assertThat(dto.getRevive()).isGreaterThan(0);
        assertThat(dto.getCreatedTime()).isBetween(beforeTestTime, afterTestTime);
    }

    @Test
    public void When_GetRankStatNormalUser_Expect_Good() {
        RankStatDto dto = ubiApi.getRankStat(platform, username, "apac",-1);
        LocalDateTime afterTestTime = LocalDateTime.now();

        assertThat(dto.getRank()).isGreaterThan(0);
        assertThat(dto.getMaxMmr()).isGreaterThan(0);
        assertThat(dto.getMaxMmr()).isGreaterThan(0);
        assertThat(dto.getMmr()).isGreaterThan(0);
        assertThat(dto.getKills()).isGreaterThan(0);
        assertThat(dto.getDeath()).isGreaterThan(0);
        assertThat(dto.getSeason()).isGreaterThan(0);
        assertThat(dto.getRegion()).isEqualTo("apac");
        assertThat(dto.getWins()).isGreaterThan(0);
        assertThat(dto.getLosses()).isGreaterThan(0);
        assertThat(dto.getAbandons()).isGreaterThanOrEqualTo(0);
        assertThat(dto.getCreatedTime()).isBetween(beforeTestTime, afterTestTime);
    }


    @Test
    public void When_GetRankStatNormalUserBefore13Season_Expect_Good() {
        RankStatDto dto = ubiApi.getRankStat(platform, username, "apac", 12);
        LocalDateTime afterTestTime = LocalDateTime.now();

        assertThat(dto.getRank()).isGreaterThan(0);
        assertThat(dto.getMaxMmr()).isGreaterThan(0);
        assertThat(dto.getMaxMmr()).isGreaterThan(0);
        assertThat(dto.getMmr()).isGreaterThan(0);
        assertThat(dto.getKills()).isEqualTo(0); // 시즌 13 이전에는 Kill, Death 가 0으로 들어옴
        assertThat(dto.getDeath()).isEqualTo(0);
        assertThat(dto.getSeason()).isGreaterThan(0);
        assertThat(dto.getRegion()).isEqualTo("apac");
        assertThat(dto.getWins()).isGreaterThan(0);
        assertThat(dto.getLosses()).isGreaterThan(0);
        assertThat(dto.getAbandons()).isGreaterThanOrEqualTo(0);
        assertThat(dto.getCreatedTime()).isBetween(beforeTestTime, afterTestTime);
    }


    @Test
    public void When_GetNormalUserProfile_Expect_Good() {
        ubiApi.getProfile(platform, username);
    }

    @Test(expected = R6NotFoundPlayerProfileException.class)
    public void When_GetAbnormalUserProfile_Expect_R6NotFoundPlayerProfile() {
        String wrongUsername = "sjkdlujv89123";
        ubiApi.getProfile(platform, wrongUsername);
    }
}
