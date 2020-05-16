package org.example.springboot.r6api.dto;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Getter
public class RankStatDto {
    int rank;
    int maxRank;
    int mmr;
    int maxMmr;
    int kills;
    int death;

    int season;
    String region;
    int wins;
    int losses;
    int abandons;
    LocalDateTime createdTime;

    public void setCreatedTime(LocalDateTime time) {
        this.createdTime = time;
    }
}
