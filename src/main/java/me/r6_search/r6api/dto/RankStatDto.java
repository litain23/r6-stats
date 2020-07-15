package me.r6_search.r6api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import me.r6_search.domain.rankstat.RankStat;

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

    public RankStatDto(RankStat rankStat) {
        this.mmr = rankStat.getMmr();
        this.maxMmr = rankStat.getMaxMmr();
        this.rank = rankStat.getRank();
        this.rankString = rankStat.getRankString();
        this.maxRank = rankStat.getMaxRank();
        this.maxRankString = rankStat.getMaxRankString();
        this.kills = rankStat.getKills();
        this.death = rankStat.getDeath();
        this.season = rankStat.getSeason();
        this.region = rankStat.getRegion();
        this.wins = rankStat.getWins();
        this.losses = rankStat.getLosses();
        this.abandons = rankStat.getAbandons();
        this.createdTime = rankStat.getCreatedTime();
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
