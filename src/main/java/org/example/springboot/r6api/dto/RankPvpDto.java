package org.example.springboot.r6api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Getter
public class RankPvpDto {
    int death;
    int kills;
    int matchLost;
    int matchWon;
    int matchPlayed;
    int timePlayed;
    LocalDateTime createdTime;

    @Builder
    public RankPvpDto(int death, int kills, int matchLost, int matchWon, int matchPlayed, int timePlayed) {
        this.death = death;
        this.kills = kills;
        this.matchLost = matchLost;
        this.matchWon = matchWon;
        this.matchPlayed = matchPlayed;
        this.timePlayed = timePlayed;
        this.createdTime = LocalDateTime.now();
    }
}
