package org.example.springboot.domain.operators;

import lombok.Builder;
import lombok.Getter;
import org.example.springboot.domain.player.Player;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
public class Operators {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name="PLAYER_ID")
    private Player player;

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
    private LocalDateTime createTime;

    public Operators() { }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Builder
    public Operators(String name, String operatorIndex, String category, int kills, int death, int headShot, int meleeKills, int totalXp, int timePlayed, int roundWon, int roundLost) {
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
}
