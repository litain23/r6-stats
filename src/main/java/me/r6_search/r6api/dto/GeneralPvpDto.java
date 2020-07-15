package me.r6_search.r6api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Getter
public class GeneralPvpDto {
    int death;
    int kills;
    int matchLost;
    int matchWon;
    int matchPlayed;
    int timePlayed;
    int penetrationKills;
    int meleeKills;
    int killAssists;
    int headShot;
    int revive;
    int bulletHit;
    LocalDateTime createdTime;

    @Builder
    public GeneralPvpDto(int death, int kills, int matchLost, int matchWon, int matchPlayed, int timePlayed, int penetrationKills, int meleeKills, int killAssists, int headShot, int revive, int bulletHit) {
        this.death = death;
        this.kills = kills;
        this.matchLost = matchLost;
        this.matchWon = matchWon;
        this.matchPlayed = matchPlayed;
        this.timePlayed = timePlayed;
        this.penetrationKills = penetrationKills;
        this.meleeKills = meleeKills;
        this.killAssists = killAssists;
        this.headShot = headShot;
        this.revive = revive;
        this.bulletHit = bulletHit;
        this.createdTime = LocalDateTime.now();
    }
}
