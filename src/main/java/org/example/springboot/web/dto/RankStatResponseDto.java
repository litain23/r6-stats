package org.example.springboot.web.dto;

import lombok.AccessLevel;
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
    String maxRankString;
    String rankString;


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
        this.maxRankString = mmrToRankString(stat.getMaxRank());
        this.rankString = mmrToRankString(stat.getRank());
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
        this.maxRankString = mmrToRankString(rankStatDto.getMaxRank());
        this.rankString = mmrToRankString(rankStatDto.getRank());
    }

    private String mmrToRankString(int rank) {
        String rankString;
        String classes = "UNRANK";
        int level = 0;

        if(rank >= 1 && rank <= 5){
            classes = "COPPER";
            level = 6 - rank;
        } else if(rank >= 6 && rank <= 10) {
            classes = "BRONZE";
            level = 11 - rank;
        } else if(rank >= 11 && rank <= 15) {
            classes = "SILVER";
            level = 16 - rank;
        } else if(rank >= 16 && rank <=18) {
            classes = "GOLD";
            level = 19 - rank;
        } else if(rank >= 19 && rank <= 21) {
            classes = "PLATINUM";
            level = 22 - rank;
        } else if(rank == 22) {
            classes = "DIAMOND";
        } else if(rank == 23) {
            classes = "CHAMPIONS";
        }

        if(classes.equals("UNRANK") || classes.equals("DIAMOND") || classes.equals("CHAMPIONS")) {
            rankString = classes;
        } else {
            rankString = classes + " " + level;
        }
        return rankString;
    }
}
