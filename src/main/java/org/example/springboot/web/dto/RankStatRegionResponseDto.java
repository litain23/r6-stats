package org.example.springboot.web.dto;

import lombok.Data;

import java.util.List;

@Data
public class RankStatRegionResponseDto {
    String region;
    RankStatResponseDto rankStat;

    public RankStatRegionResponseDto(String region, RankStatResponseDto rankStat) {
        this.region = region;
        this.rankStat = rankStat;
    }
}
