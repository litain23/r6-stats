package org.example.springboot.web.dto;

import lombok.Getter;
import org.example.springboot.domain.operators.Operators;

@Getter
public class OperatorListResponseDto {
    private String name;
    private String index;
    private String uniqueStatisticName;
    private int uniqueStatisticOasisId;
    private int kills;
    private int deaths;
    private int headShot;
    private int meleeKills;
    private int totalXp;
    private int timePlayed;
    private int roundWon;
    private int roundLost;

    public OperatorListResponseDto(Operators operator) {
        this.name = operator.getName();
        this.index = operator.getIndex();
        this.uniqueStatisticOasisId = operator.getUniqueStatisticOasisId();
        this.uniqueStatisticName = operator.getUniqueStatisticName();
        this.kills = operator.getKills();
        this.deaths = operator.getDeaths();
        this.headShot = operator.getHeadShot();
        this.meleeKills = operator.getMeleeKills();
        this.totalXp = operator.getTotalXp();
        this.timePlayed = operator.getTimePlayed();
        this.roundLost = operator.getRoundLost();
        this.roundWon = operator.getRoundWon();
    }

}
