package org.example.springboot.web.dto;

import lombok.Getter;
import org.example.springboot.domain.operator.Operator;
import org.example.springboot.r6api.dto.OperatorDto;

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

    public OperatorListResponseDto(Operator operator) {
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

    public OperatorListResponseDto(OperatorDto operatorDto) {
        this.name = operatorDto.getName();
        this.operatorIndex = operatorDto.getOperatorIndex();
        this.kills = operatorDto.getKills();
        this.death = operatorDto.getDeath();
        this.headShot = operatorDto.getHeadShot();
        this.meleeKills = operatorDto.getMeleeKills();
        this.totalXp = operatorDto.getTotalXp();
        this.timePlayed = operatorDto.getTimePlayed();
        this.roundLost = operatorDto.getRoundLost();
        this.roundWon = operatorDto.getRoundWon();
        this.createdTime = operatorDto.getCreatedTime();
    }

}
