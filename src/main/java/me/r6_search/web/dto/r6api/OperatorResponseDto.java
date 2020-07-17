package me.r6_search.web.dto.r6api;

import lombok.Getter;
import me.r6_search.r6api.dto.OperatorDto;

import java.time.LocalDateTime;

@Getter
public class OperatorResponseDto {
    private String name;
    private String operatorIndex;
    private String category;
    private int kills;
    private int death;
    private int headShot;
    private int meleeKills;
    private int totalXp;
    private int timePlayed;
    private int roundWon;
    private int roundLost;
    private LocalDateTime createdTime;

    public OperatorResponseDto(OperatorDto operatorDto) {
        this.name = operatorDto.getName();
        this.category = operatorDto.getCategory();
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
