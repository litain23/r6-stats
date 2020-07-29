package me.r6_search.web.dto.r6api;

import lombok.Data;
import me.r6_search.model.rankpvp.RankPvp;
import me.r6_search.r6api.dto.RankPvpDto;

import java.time.LocalDateTime;

@Data
public class RankPvpResponseDto {
    int death;
    int kills;
    int matchLost;
    int matchWon;
    int matchPlayed;
    int timePlayed;
    LocalDateTime createdTime;

    public RankPvpResponseDto(RankPvp rankPvp) {
        this.death = rankPvp.getDeath();
        this.kills = rankPvp.getKills();
        this.matchLost = rankPvp.getMatchLost();
        this.matchWon = rankPvp.getMatchWon();
        this.matchPlayed = rankPvp.getMatchPlayed();
        this.timePlayed = rankPvp.getTimePlayed();
        this.createdTime = rankPvp.getCreatedTime();
    }

    public RankPvpResponseDto(RankPvpDto rankPvpDto) {
        this.death = rankPvpDto.getDeath();
        this.kills = rankPvpDto.getKills();
        this.matchLost = rankPvpDto.getMatchLost();
        this.matchWon = rankPvpDto.getMatchWon();
        this.matchPlayed = rankPvpDto.getMatchPlayed();
        this.timePlayed = rankPvpDto.getTimePlayed();
        this.createdTime = rankPvpDto.getCreatedTime();
    }
}
