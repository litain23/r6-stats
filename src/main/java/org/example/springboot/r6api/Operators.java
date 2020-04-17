package org.example.springboot.r6api;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Operators {
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

    @Builder
    public Operators(String name, String index, String uniqueStatisticName, int uniqueStatisticPvp, int kills, int death, int headshot, int meleekills, int totalxp, int timeplayed, int roundwon, int roundlost) {
        this.name = name;
        this.index = index;
        this.uniqueStatisticName = uniqueStatisticName;
        this.uniqueStatisticPvp = uniqueStatisticPvp;
        this.kills = kills;
        this.death = death;
        this.headshot = headshot;
        this.meleekills = meleekills;
        this.totalxp = totalxp;
        this.timeplayed = timeplayed;
        this.roundwon = roundwon;
        this.roundlost = roundlost;
    }
}
