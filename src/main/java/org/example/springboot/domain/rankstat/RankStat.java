package org.example.springboot.domain.rankstat;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Map;

@Getter
@Entity
public class RankStat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int rank;
    private int maxRank;
    private int mmr;
    private int maxMmr;

    private int kills;
    private int death;

    private int season;
    private String region;
    private int wins;
    private int losses;
    private int abandons;
    private String updateTime;

//  UBI API에서는 제공하지만 사용자에게는 제공할 필요가 없다고 판단
//    private double skillMean;
//    private int nextRankMmr;
//    private String boardId;
//    private double skillStdev;
//    private double lastMatchSkillStdevChange;
//    private int lastMatchMmrChange;
//    private int topRankPosition;
//    private double lastMatchSkillMeanChange;
//    private int previousRankMmr;
//    private int lastMatchResult;

    public RankStat() {}

    @Builder
    public RankStat(int rank, int maxRank, int mmr, int maxMmr, int kills, int death, int season, String region, int wins, int losses, int abandons, String updateTime) {
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
        this.updateTime = updateTime;
    }
}
