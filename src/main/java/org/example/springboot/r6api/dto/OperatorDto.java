package org.example.springboot.r6api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Getter
public class OperatorDto {
    String name;
    String operatorIndex;
    String category;
    int kills;
    int death;
    int headShot;
    int meleeKills;
    int totalXp;
    int timePlayed;
    int roundWon;
    int roundLost;
    private LocalDateTime createdTime;

    @Builder
    public OperatorDto(String name, String operatorIndex, String category, int kills, int death, int headShot, int meleeKills, int totalXp, int timePlayed, int roundWon, int roundLost) {
        this.name = name;
        this.operatorIndex = operatorIndex;
        this.category = category;
        this.kills = kills;
        this.death = death;
        this.headShot = headShot;
        this.meleeKills = meleeKills;
        this.totalXp = totalXp;
        this.timePlayed = timePlayed;
        this.roundWon = roundWon;
        this.roundLost = roundLost;
        this.createdTime = LocalDateTime.now();
    }
}
