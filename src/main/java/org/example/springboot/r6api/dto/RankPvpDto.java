package org.example.springboot.r6api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.example.springboot.domain.rankpvp.RankPvp;

import java.time.LocalDateTime;

@ToString
@Getter
public class RankPvpDto {
    int death;
    int kills;
    int matchLost;
    int matchWon;
    int matchPlayed;
    int timePlayed;
    LocalDateTime createdTime;

    @Builder
    public RankPvpDto(int death, int kills, int matchLost, int matchWon, int matchPlayed, int timePlayed) {
        this.death = death;
        this.kills = kills;
        this.matchLost = matchLost;
        this.matchWon = matchWon;
        this.matchPlayed = matchPlayed;
        this.timePlayed = timePlayed;
        this.createdTime = LocalDateTime.now();
    }

    public RankPvpDto(RankPvp rankpvp) {
        this.death = rankpvp.getDeath();
        this.kills = rankpvp.getKills();
        this.matchLost = rankpvp.getMatchLost();
        this.matchPlayed = rankpvp.getMatchPlayed();
        this.matchWon = rankpvp.getMatchWon();
        this.createdTime = rankpvp.getCreatedTime();
    }
}
