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
    private int headshot;
    private int meleekills;
    private int totalxp;
    private int timeplayed;
    private int roundwon;
    private int roundlost;

    public OperatorListResponseDto(Operators operator) {
        this.name = operator.getName();
        this.index = operator.getIndex();
        this.uniqueStatisticPvp = operator.getUniqueStatisticPvp();
        this.uniqueStatisticName = operator.getUniqueStatisticName();
        this.kills = operator.getKills();
        this.death = operator.getDeath();
        this.headshot = operator.getHeadshot();
        this.meleekills = operator.getMeleekills();
        this.totalxp = operator.getTotalxp();
        this.timeplayed = operator.getTimeplayed();
        this.roundlost = operator.getRoundlost();
        this.roundwon = operator.getRoundwon();
    }

}
