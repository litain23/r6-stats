package org.example.springboot.r6api;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RankStat {
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
}
