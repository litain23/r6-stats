package org.example.springboot.web.dto;

import lombok.Getter;
import org.example.springboot.r6api.dto.GeneralPvpDto;

import java.time.LocalDateTime;

@Getter
public class GeneralPvpResponseDto {
    int matchLost;
    int matchWon;
    int matchPlayed;
    int kills;
    int death;
    int penetrationKills;
    int meleeKills;
    int killAssists;
    int headShot;
    int revive;
    int bulletHit;
    int timePlayed;
    LocalDateTime createdTime;

    public GeneralPvpResponseDto(GeneralPvpDto generalPvpDto) {
        this.matchLost = generalPvpDto.getMatchLost();
        this.matchWon = generalPvpDto.getMatchWon();
        this.matchPlayed = generalPvpDto.getMatchPlayed();
        this.kills = generalPvpDto.getKills();
        this.death = generalPvpDto.getDeath();
        this.penetrationKills = generalPvpDto.getPenetrationKills();
        this.meleeKills = generalPvpDto.getMeleeKills();
        this.killAssists = generalPvpDto.getKillAssists();
        this.headShot = generalPvpDto.getHeadShot();
        this.revive = generalPvpDto.getRevive();
        this.bulletHit = generalPvpDto.getBulletHit();
        this.timePlayed = generalPvpDto.getTimePlayed();
        this.createdTime = generalPvpDto.getCreatedTime();
    }
}
