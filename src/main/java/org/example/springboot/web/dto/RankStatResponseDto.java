package org.example.springboot.web.dto;

import lombok.Data;
import org.example.springboot.domain.rankstat.RankStat;

@Data
public class RankStatResponseDto {
    private int maxMmr;
    private int death;
    private int rank;
    private int maxRank;
    private int kills;
    private int abandons;
    private int mmr;
    private int wins;
    private String region;
    private int season;
    private int losses;

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
    }
}
