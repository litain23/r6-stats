package org.example.springboot.r6api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Getter
public class RankStatDto {
    int rank;
    int maxRank;
    int mmr;
    int maxMmr;
    String maxRankString;
    String rankString;
    int kills;
    int death;

    int season;
    String region;
    int wins;
    int losses;
    int abandons;
    LocalDateTime createdTime;

    @Builder
    public RankStatDto(int rank, int maxRank, int mmr, int maxMmr, int kills, int death, int season, String region, int wins, int losses, int abandons) {
        this.rank = rank;
        this.maxRank = maxRank;
        this.mmr = mmr;
        this.maxMmr = maxMmr;
        this.kills = kills;
        this.death = death;
        this.season = season;
        this.region = region;
        this.wins = wins;
        this.losses = losses;
        this.abandons = abandons;
        this.createdTime = LocalDateTime.now();
    }

    public void setCreatedTime(LocalDateTime time) {
        this.createdTime = time;
    }
    public void setMaxRankString(String maxRankString) {
        this.maxRankString = maxRankString;
    }

    public void setRankString(String rankString) {
        this.rankString = rankString;
    }

}
