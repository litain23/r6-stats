package org.example.springboot.web.dto;

import lombok.Getter;
import org.example.springboot.domain.operators.Operators;

import java.time.LocalDateTime;

@Getter
public class OperatorListResponseDto {
    private String name;
    private String operatorIndex;
    private int kills;
    private int death;
    private int headShot;
    private int meleeKills;
    private int totalXp;
    private int timePlayed;
    private int roundWon;
    private int roundLost;
    private LocalDateTime createdTime;

    public OperatorListResponseDto(Operators operator) {
        this.name = operator.getName();
        this.operatorIndex = operator.getOperatorIndex();
        this.kills = operator.getKills();
        this.death = operator.getDeath();
        this.headShot = operator.getHeadShot();
        this.meleeKills = operator.getMeleeKills();
        this.totalXp = operator.getTotalXp();
        this.timePlayed = operator.getTimePlayed();
        this.roundLost = operator.getRoundLost();
        this.roundWon = operator.getRoundWon();
        this.createdTime = operator.getCreatedTime();
    }

}
