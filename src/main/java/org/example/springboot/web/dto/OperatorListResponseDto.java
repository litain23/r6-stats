package org.example.springboot.web.dto;

import lombok.Getter;
import org.example.springboot.r6api.Operators;

@Getter
public class OperatorListResponseDto {
    private String name;
    private String index;
    private String uniqueStatisticName;
    private int uniqueStatisticPvp;
    private int kills;
    private int death;
    private int headShot;
    private int meleeKills;
    private int totalXp;
    private int timePlayed;
    private int roundWon;
    private int roundLost;

    public OperatorListResponseDto(Operators operator) {
        this.name = operator.getName();
        this.index = operator.getIndex();
        this.uniqueStatisticPvp = operator.getUniqueStatisticPvp();
        this.uniqueStatisticName = operator.getUniqueStatisticName();
        this.kills = operator.getKills();
        this.death = operator.getDeath();
        this.headShot = operator.getHeadShot();
        this.meleeKills = operator.getMeleeKills();
        this.totalXp = operator.getTotalXp();
        this.timePlayed = operator.getTimePlayed();
        this.roundLost = operator.getRoundLost();
        this.roundWon = operator.getRoundWon();
    }

}
