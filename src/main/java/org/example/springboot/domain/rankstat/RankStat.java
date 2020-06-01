package org.example.springboot.domain.rankstat;

import lombok.Builder;
import lombok.Getter;
import org.example.springboot.domain.player.Player;
import org.example.springboot.r6api.dto.RankStatDto;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@EntityListeners(AuditingEntityListener.class)
@Entity
public class RankStat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="PLAYER_ID")
    @NotNull
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
    private LocalDateTime createdTime;

    public RankStat() {}

    @Builder
    public RankStat(int rank, int maxRank, int mmr, int maxMmr, int kills, int death, int season, String region, int wins, int losses, int abandons, Player player) {
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
        this.player = player;
    }

    @Builder
    public RankStat(RankStatDto dto, Player player) {
        this.rank = dto.getRank();
        this.maxRank = dto.getMaxRank();
        this.mmr = dto.getMmr();
        this.maxMmr = dto.getMaxMmr();
        this.kills = dto.getKills();
        this.death = dto.getDeath();
        this.season = dto.getSeason();
        this.region = dto.getRegion();
        this.wins = dto.getWins();
        this.losses = dto.getLosses();
        this.abandons = dto.getAbandons();
        this.player = player;

    }

    public void updateRankStat(RankStatDto rankStatDto) {
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
    }

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
}
