package org.example.springboot.domain.operator;

import lombok.Builder;
import lombok.Getter;
import org.example.springboot.domain.player.Player;
import org.example.springboot.domain.weeklyoperator.WeeklyOperator;
import org.example.springboot.r6api.dto.OperatorDto;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Operator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

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

    @CreationTimestamp
    private LocalDateTime createdTime;

    public Operator() { }

    @Builder
    public Operator(String name, String operatorIndex, String category, int kills, int death, int headShot, int meleeKills, int totalXp, int timePlayed, int roundWon, int roundLost) {
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
    }

    public Operator(OperatorDto operatorDto) {
        this.name = operatorDto.getName();
        this.operatorIndex = operatorDto.getOperatorIndex();
        this.category = operatorDto.getCategory();
        this.kills = operatorDto.getKills();
        this.death = operatorDto.getDeath();
        this.headShot = operatorDto.getHeadShot();
        this.meleeKills = operatorDto.getMeleeKills();
        this.totalXp = operatorDto.getTotalXp();
        this.timePlayed = operatorDto.getTimePlayed();
        this.roundLost = operatorDto.getRoundLost();
        this.roundWon = operatorDto.getRoundWon();
    }
}
