package org.example.springboot.r6api;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Operators {
    private String name;
    private String index;
    private String category;
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

    @Builder
    public Operators(String name, String index, String uniqueStatisticName, int uniqueStatisticPvp, int kills, int death, int headShot, int meleeKills, int totalXp, int timePlayed, int roundWon, int roundLost) {
        this.name = name;
        this.index = index;
        this.uniqueStatisticName = uniqueStatisticName;
        this.uniqueStatisticPvp = uniqueStatisticPvp;
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
