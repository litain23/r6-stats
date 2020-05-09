package org.example.springboot.web.dto;

import lombok.Data;
import org.example.springboot.domain.rankpvp.RankPvp;

@Data
public class RankPvpResponseDto {
    int death;
    int kills;
    int matchLost;
    int matchWon;
    int matchPlayed;
    int timePlayed;

    public RankPvpResponseDto(RankPvp casualPvp) {
        this.death = casualPvp.getDeath();
        this.kills = casualPvp.getKills();
        this.matchLost = casualPvp.getMatchLost();
        this.matchWon = casualPvp.getMatchWon();
        this.matchPlayed = casualPvp.getMatchPlayed();
        this.timePlayed = casualPvp.getTimePlayed();
    }
}
