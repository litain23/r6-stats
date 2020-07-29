package me.r6_search.r6api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import me.r6_search.model.operator.Operator;

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
    LocalDateTime createdTime;

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

    public OperatorDto(Operator operator) {
        this.name = operator.getName();
        this.operatorIndex = operator.getOperatorIndex();
        this.category = operator.getCategory();
        this.kills = operator.getKills();
        this.death = operator.getDeath();
        this.headShot = operator.getHeadShot();
        this.meleeKills = operator.getMeleeKills();
        this.totalXp = operator.getTotalXp();
        this.timePlayed = operator.getTimePlayed();
        this.roundWon = operator.getRoundWon();
        this.roundLost = operator.getRoundLost();
        this.createdTime = operator.getCreatedTime();
    }
}
