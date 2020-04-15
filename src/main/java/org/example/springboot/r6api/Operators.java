package org.example.springboot.r6api;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Operators {
    private String name;
    private String category;
    private String index;
    private String uniqueStatisticPvp;
    private String uniqueStatisticPve;
    private int uniqueStatisticId;
    private int kills;
    private int death;
    private int headshot;
    private int meleekills;
    private int totalxp;
    private int timeplayed;
    private int roundwon;
    private int roundlost;

    @Builder
    public Operators(int kills, int death, int headshot, int meleekills, int totalxp, int timeplayed, int roundwon, int roundlost) {
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
