package org.example.springboot.web.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class GeneralPvpResponseDto {
    private int totalMatchLost;
    private int totalMatchWon;
    private int totalMatchPlayed;
    private int totalKills;
    private int totalDeath;
    private int totalPenetrationKills;
    private int totalMeleeKills;
    private int totalKillAssists;
    private int totalHeadShot;
    private int totalRevive;
    private int totalBulletHit;
    private int totalTimePlayed;

    @Builder
    public GeneralPvpResponseDto(int totalMatchLost, int totalMatchWon, int totalMatchPlayed, int totalKills, int totalDeath, int totalPenetrationKills, int totalMeleeKills, int totalKillAssists, int totalHeadShot, int totalRevive, int totalBulletHit, int totalTimePlayed) {
        this.totalMatchLost = totalMatchLost;
        this.totalMatchWon = totalMatchWon;
        this.totalMatchPlayed = totalMatchPlayed;
        this.totalKills = totalKills;
        this.totalDeath = totalDeath;
        this.totalPenetrationKills = totalPenetrationKills;
        this.totalMeleeKills = totalMeleeKills;
        this.totalKillAssists = totalKillAssists;
        this.totalHeadShot = totalHeadShot;
        this.totalRevive = totalRevive;
        this.totalBulletHit = totalBulletHit;
        this.totalTimePlayed = totalTimePlayed;
    }
}
