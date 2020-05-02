package org.example.springboot.web.dto;

import lombok.Getter;
import org.example.springboot.domain.generalpvp.GeneralPvp;

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

    public GeneralPvpResponseDto(GeneralPvp pvp) {
        this.totalMatchLost = pvp.getTotalMatchLost();
        this.totalMatchWon = pvp.getTotalMatchWon();
        this.totalMatchPlayed = pvp.getTotalMatchPlayed();
        this.totalKills = pvp.getTotalKills();
        this.totalDeath = pvp.getTotalDeath();
        this.totalPenetrationKills = pvp.getTotalPenetrationKills();
        this.totalMeleeKills = pvp.getTotalMeleeKills();
        this.totalKillAssists = pvp.getTotalKillAssists();
        this.totalHeadShot = pvp.getTotalHeadShot();
        this.totalRevive = pvp.getTotalRevive();
        this.totalBulletHit = pvp.getTotalBulletHit();
        this.totalTimePlayed = pvp.getTotalTimePlayed();
    }
}
