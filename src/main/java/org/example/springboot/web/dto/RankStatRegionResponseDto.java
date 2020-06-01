package org.example.springboot.web.dto;

import lombok.Data;

import java.util.List;

@Data
public class RankStatRegionResponseDto {
    String region;
    List<RankStatResponseDto> rankStat;

    public RankStatRegionResponseDto(String region, List<RankStatResponseDto> rankStat) {
        this.region = region;
        this.rankStat = rankStat;
    }
}
