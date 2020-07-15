package me.r6_search.web.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class RankStatSeasonResponseDto {
    int season;
    List<RankStatRegionResponseDto> seasonData;

    @Builder
    public RankStatSeasonResponseDto(int season, List<RankStatRegionResponseDto> seasonData) {
        this.season = season;
        this.seasonData = seasonData;
    }
}
