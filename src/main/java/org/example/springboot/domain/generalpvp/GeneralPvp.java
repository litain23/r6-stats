package org.example.springboot.domain.generalpvp;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class GeneralPvp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    public GeneralPvp() { }

    @Builder
    public GeneralPvp(int totalMatchLost, int totalMatchWon, int totalMatchPlayed, int totalKills, int totalDeath, int totalPenetrationKills, int totalMeleeKills, int totalKillAssists, int totalHeadShot, int totalRevive, int totalTimePlayed, int totalBulletHit) {
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
