package org.example.springboot.web.dto;

import lombok.Data;
import org.example.springboot.domain.rankstat.RankStat;
import org.example.springboot.r6api.dto.RankStatDto;

import java.time.LocalDateTime;

@Data
public class RankStatResponseDto {
    int maxMmr;
    int death;
    int rank;
    int maxRank;
    int kills;
    int abandons;
    int mmr;
    int wins;
    String region;
    int season;
    int losses;
    LocalDateTime createdTime;


    public RankStatResponseDto(RankStat stat) {
        this.maxMmr = stat.getMaxMmr();
        this.death = stat.getDeath();
        this.rank = stat.getRank();
        this.maxRank = stat.getMaxRank();
        this.kills = stat.getKills();
        this.abandons = stat.getAbandons();
        this.mmr = stat.getMmr();
        this.wins = stat.getWins();
        this.region = stat.getRegion();
        this.season = stat.getSeason();
        this.losses = stat.getLosses();
        this.createdTime = stat.getCreatedTime();
    }

    public RankStatResponseDto(RankStatDto rankStatDto) {
        this.maxMmr = rankStatDto.getMaxMmr();
        this.death = rankStatDto.getDeath();
        this.rank = rankStatDto.getRank();
        this.maxRank = rankStatDto.getMaxRank();
        this.kills = rankStatDto.getKills();
        this.abandons = rankStatDto.getAbandons();
        this.mmr = rankStatDto.getMmr();
        this.wins = rankStatDto.getWins();
        this.region = rankStatDto.getRegion();
        this.season = rankStatDto.getSeason();
        this.losses = rankStatDto.getLosses();
        this.createdTime = rankStatDto.getCreatedTime();
    }




}
