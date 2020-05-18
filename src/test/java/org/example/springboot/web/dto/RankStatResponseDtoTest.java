package org.example.springboot.web.dto;

import org.example.springboot.domain.rankstat.RankStat;
import org.example.springboot.r6api.dto.RankStatDto;
import org.example.springboot.web.dto.RankStatResponseDto;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RankStatResponseDtoTest {

    @Test
    public void When_GiveMmrNormalRange_Expect_Good() {
        RankStatDto rankStatDto = RankStatDto.builder().maxRank(11).rank(10).build();
        RankStatResponseDto responseDto = new RankStatResponseDto(rankStatDto);
        assertThat(responseDto.getRankString()).isEqualTo("BRONZE 1");
        assertThat(responseDto.getMaxRankString()).isEqualTo("SILVER 5");

        rankStatDto = RankStatDto.builder().maxRank(0).rank(0).build();
        responseDto = new RankStatResponseDto(rankStatDto);
        assertThat(responseDto.getRankString()).isEqualTo("UNRANK");
        assertThat(responseDto.getMaxRankString()).isEqualTo("UNRANK");

        rankStatDto = RankStatDto.builder().maxRank(23).rank(22).build();
        responseDto = new RankStatResponseDto(rankStatDto);
        assertThat(responseDto.getRankString()).isEqualTo("DIAMOND");
        assertThat(responseDto.getMaxRankString()).isEqualTo("CHAMPIONS");
    }
}
