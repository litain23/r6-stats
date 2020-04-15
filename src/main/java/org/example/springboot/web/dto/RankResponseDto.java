package org.example.springboot.web.dto;

import lombok.Data;
import org.example.springboot.r6api.RankStat;

@Data
public class RankResponseDto {
    private int maxMmr;
    private double skillMean;
    private int deaths;
    private int nextRankMmr;
    private int rank;
    private int maxRank;
    private String boardId;
    private double skillStdev;
    private int kills;
    private double lastMatchSkillStdevChange;
    private String updateTime;
    private int lastMatchMmrChange;
    private int abandons;
    private int topRankPosition;
    private double lastMatchSkillMeanChange;
    private int mmr;
    private int previousRankMmr;
    private int lastMatchResult;
    private int wins;
    private String region;
    private int season;
    private int losses;

    public RankResponseDto(RankStat stat) {
        this.maxMmr = stat.getMaxMmr();
        this.skillMean = stat.getSkillMean();
        this.deaths = stat.getDeaths();
        this.nextRankMmr = stat.getNextRankMmr();
        this.rank = stat.getRank();
        this.maxRank = stat.getMaxRank();
        this.boardId = stat.getBoardId();
        this.skillMean = stat.getSkillMean();
        this.kills = stat.getKills();
        this.lastMatchSkillStdevChange = stat.getLastMatchSkillStdevChange();
        this.updateTime = stat.getUpdateTime();
        this.lastMatchMmrChange = stat.getLastMatchMmrChange();
        this.abandons = stat.getAbandons();
        this.topRankPosition = stat.getTopRankPosition();
        this.lastMatchSkillMeanChange = stat.getLastMatchSkillMeanChange();
        this.mmr = stat.getMmr();
        this.previousRankMmr = stat.getPreviousRankMmr();
        this.lastMatchResult = stat.getLastMatchResult();
        this.wins = stat.getWins();
        this.region = stat.getRegion();
        this.season = stat.getSeason();
        this.losses = stat.getLosses();
        this.skillStdev = stat.getSkillStdev();
    }
}
