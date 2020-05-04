package org.example.springboot.domain.rankstat;

import lombok.Builder;
import lombok.Getter;
import org.example.springboot.domain.player.Player;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
public class RankStat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="PLAYER_ID")
    private Player player;

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

    @CreationTimestamp
    private LocalDateTime createTime;
    @UpdateTimestamp
    private LocalDateTime modifyTime;

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
    public RankStat(int rank, int maxRank, int mmr, int maxMmr, int kills, int death, int season, String region, int wins, int losses, int abandons) {
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
    }

    public void updateRankStat(RankStat stat) {
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

    public void setPlayer(Player player) {
        this.player = player;
    }
}
