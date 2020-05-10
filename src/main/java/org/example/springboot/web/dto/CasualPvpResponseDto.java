package org.example.springboot.web.dto;

import lombok.Data;
import org.example.springboot.domain.casualpvp.CasualPvp;

import java.time.LocalDateTime;

@Data
public class CasualPvpResponseDto {
    int death;
    int kills;
    int matchLost;
    int matchWon;
    int matchPlayed;
    int timePlayed;
    LocalDateTime createdTime;

    public CasualPvpResponseDto(CasualPvp casualPvp) {
        this.death = casualPvp.getDeath();
        this.kills = casualPvp.getKills();
        this.matchLost = casualPvp.getMatchLost();
        this.matchWon = casualPvp.getMatchWon();
        this.matchPlayed = casualPvp.getMatchPlayed();
        this.timePlayed = casualPvp.getTimePlayed();
        this.createdTime = casualPvp.getCreatedTime();
    }
}
